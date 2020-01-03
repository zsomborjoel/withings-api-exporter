package com.api.exporter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.api.exporter.model.ApplicationProperties;
import com.api.exporter.model.Token;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TokenRefresher {

    private static final Logger logger = LoggerFactory.getLogger(TokenRefresher.class);

    @Value("${withings.api.tokenhost}")
    private String apiTokenHost;

    @Value("${withings.api.clientid}")
    private String clientId;

    @Value("${withings.api.clientsecret}")
    private String clientSecret;

    @Value("${token.path}")
    private String tokenFile;

    @Autowired
    private ApplicationProperties applicationProperties;

    /**
     * Saves token to backup file
     * @param json
     * @throws IOException
     */
    private void saveToJson(String json) throws IOException {
        File resource = new File(tokenFile);

        FileWriter fileWriter = new FileWriter(resource);
        fileWriter.write(json);
        fileWriter.flush();
        fileWriter.close();
    
        logger.info("Successfully wrote token to the token file");
    }
    
    /**
     * First I get the token in string format and save it for safety
     * @return API token in string format
     * @throws Exception
     */
    public String getJsonToken() throws Exception {
        String output;
        String responseAsString;
        StringBuffer response = new StringBuffer();

        /**
        String curl = "curl --data " +
                                "\"" +
                                "grant_type=refresh_token" +
                                "&client_id="     + clientId +
                                "&client_secret=" + clientSecret +
                                "&refresh_token=" + applicationProperties.getRefreshToken() + 
                                "\"" +
                                " \"" + apiTokenHost + "\"";     
        */

        /**
         * 2020-01-03 Zsombor Gyurkovics
         * 
         * Bad suboptimal solution to get token. Will be changed.  
         * I had to use this temporary solution becouse the java.lang.Process 
         * not performing same way on linux and windows with the upper curl command.
         */
        String curl = "python curl.py " + 
                                clientId + " " + 
                                clientSecret + " " + 
                                applicationProperties.getRefreshToken() + " " +
                                apiTokenHost;

        Process process = Runtime.getRuntime().exec(curl);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        process.waitFor();

        while ((output = bufferedReader.readLine()) != null) {
            response.append(output);
        }
        bufferedReader.close();

        responseAsString = response.toString();

        saveToJson(responseAsString);

        logger.info("New token: " + responseAsString);

        return responseAsString;
    }

    /**
     * Gives back API token
     * @return Token object
     * @throws Exception
     */
    public Token getResponse() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        TypeReference<Token> typeReference = new TypeReference<Token>(){};
        String jsonToken = getJsonToken();
        
        return mapper.readValue(jsonToken, typeReference);   
	}
    
    /**
     * Runs refresh mechanism
     * 
     */
    public void refresh() {    
        try {
            Token token = getResponse();
            applicationProperties.setAccessToken(token.getAccessToken());
            applicationProperties.setRefreshToken(token.getRefreshToken());
            logger.info("Tokens are refreshed successfully");
        } catch(Exception e) {
            logger.error("Exception occured during token refresh ", e);
        }
    }

}