package com.api.exporter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.api.exporter.model.Token;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class TokenRefresher {

    private static final Logger logger = LoggerFactory.getLogger(TokenRefresher.class);

    @Value("${withings.api.tokenhost}")
    private String apiHost;

    @Value("${withings.api.clientid}")
    private String clientId;

    @Value("${withings.api.clientsecret}")
    private String clientSecret;

    @Value("${withings.api.refreshtoken}")
    private String refreshToken;

    @Value("${withings.api.accesstoken}")
    private String accessToken;

    @Autowired
    private ApplicationProperties applicationProperties;

    public void saveToJson(String json) throws IOException {

        File resource = new ClassPathResource("json/token.json").getFile();

        FileWriter fileWriter = new FileWriter(resource);
        fileWriter.write(json);
        fileWriter.flush();
        fileWriter.close();
    
        logger.info("Successfully wrote token to the token file");

    }


    
    public String getJsonToken() throws Exception {

        String output;
        StringBuffer response = new StringBuffer();

        String curl = "curl --data " +
                                "\"" +
                                "grant_type=refresh_token" +
                                "&client_id="     + clientId +
                                "&client_secret=" + clientSecret +
                                "&refresh_token=" + refreshToken + 
                                "\"" +
                                " \"" + apiHost + "\"";       

        System.out.println(curl);
        Process process = Runtime.getRuntime().exec(curl);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        while ((output = bufferedReader.readLine()) != null) {
            response.append(output);
        }
        bufferedReader.close();

        saveToJson(response.toString());

        return response.toString();

    }

    
    public Token getResponse() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		TypeReference<Token> typeReference = new TypeReference<Token>(){};
        String jsonToken = getJsonToken();
        
        return mapper.readValue(jsonToken,typeReference);
        
	}
    

    public void refresh() throws Exception {
        
        Token token = getResponse();
        applicationProperties.setAccessToken(token.getAccessToken());
        applicationProperties.setRefreshToken(token.getRefreshToken());

    }

}