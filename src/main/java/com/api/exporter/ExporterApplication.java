package com.api.exporter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;

import com.api.exporter.model.HeartListApi.ListResponse;
import com.api.exporter.model.HeartGetApi.GetBody;
import com.api.exporter.model.HeartGetApi.GetResponse;
import com.api.exporter.model.HeartListApi.Series;
import com.api.exporter.repository.HeartGetRepository;
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

	private final LocalDate now = LocalDate.now();
	private final LocalDate pevious = now.minusDays(1);

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
				for (Series element : series) {
					element.setEnddate(now);
					element.setStartdate(pevious);
				}
				hlRepository.saveAll(series);
				System.out.println("Series Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save series: " + e.getMessage());
			}
	    };
	}

	// Temporal test until get token for test with API
	@Bean
	CommandLineRunner runner2(HeartGetRepository hlRepository){
	    return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			TypeReference<GetResponse> typeReference = new TypeReference<GetResponse>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/test2.json");
			try {
				GetResponse get = mapper.readValue(inputStream,typeReference);
				GetBody body = get.getBody();
				hlRepository.save(body);
				System.out.println("Body Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save body: " + e.getMessage());
			}
	    };
	}

}
