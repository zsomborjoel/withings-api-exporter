package com.api.exporter.service;

import java.io.IOException;
import java.io.InputStream;

import com.api.exporter.model.HeartGetApi.GetBody;
import com.api.exporter.model.HeartGetApi.GetResponse;
import com.api.exporter.repository.HeartGetRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HeartGetService {

    private static final Logger logger = LoggerFactory.getLogger(HeartGetService.class);

    @Autowired
    private HeartGetRepository heartGetRepository;

    private RestTemplate restTemplate;

    @Value("${withings.api.host}")
    private String apiHost;

    public GetResponse getResponse() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		TypeReference<GetResponse> typeReference = new TypeReference<GetResponse>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/test2.json");
		
		return mapper.readValue(inputStream,typeReference);
		//return restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", GetResponse.class);
	}
  
	public GetBody getBodyData() throws IOException {
        GetResponse getResponse = getResponse();
        
		return getResponse.getBody();
	}
	
	public void saveBody() throws IOException {
		GetBody getBody = getBodyData();
		
		heartGetRepository.save(getBody);
	}

    //@Bean
	private void runHeartGet() throws IOException {
        saveBody();
    }

}