package com.api.exporter;

import java.io.IOException;
import java.io.InputStream;

import com.api.exporter.model.HeartListApi.ListResponse;
import com.api.exporter.model.HeartListApi.Series;
import com.api.exporter.repository.HeartListRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ExporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExporterApplication.class, args);
	}


	// Temporal test until get token for test with API
	@Bean
	CommandLineRunner runner(HeartListRepository hlRepository){
	    return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			TypeReference<ListResponse> typeReference = new TypeReference<ListResponse>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/test.json");
			try {
				ListResponse list = mapper.readValue(inputStream,typeReference);
				List<Series> series = list.getBody().getSeries();
				hlRepository.saveAll(series);
				System.out.println("Series Saved!");
			} catch (IOException e){
				System.out.println("Unable to save series: " + e.getMessage());
			}
	    };
	}

}
