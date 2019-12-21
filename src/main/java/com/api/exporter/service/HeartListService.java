package com.api.exporter.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import com.api.exporter.model.HeartListApi.ListResponse;
import com.api.exporter.model.HeartListApi.Series;
import com.api.exporter.repository.HeartListRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class HeartListService {

	private static final Logger logger = LoggerFactory.getLogger(HeartListService.class);

	private final LocalDate now = LocalDate.now();

	private final LocalDate previousDay = now.minusDays(1);
	
    @Autowired
	private HeartListRepository hlRepository;
	
	private RestTemplate restTemplate;
	
	@Value("${withings.api.path}")
	private String apiPath;

	public ListResponse getResponse() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		TypeReference<ListResponse> typeReference = new TypeReference<ListResponse>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/test.json");
		
		return mapper.readValue(inputStream,typeReference);
		//return restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", ListResponse.class);
	}
  
	public List<Series> getSeriesData() throws IOException {
		ListResponse listResponse = getResponse();

		return listResponse.getBody().getSeries();
	}

	public List<Integer> getSignalIds() throws IOException {
		List<Integer> signalIds = new ArrayList<>();
		List<Series> series = getSeriesData();
		series.forEach(e -> signalIds.add(e.getEcg().getSignalid()));
		
		return signalIds;
	}
	
	public void saveSeries() throws IOException {
		List<Series> series = getSeriesData();
		series.forEach(s -> {
			s.setEnddate(now); 
			s.setStartdate(previousDay);
						});

		hlRepository.saveAll(series);
	}

	@Bean
	private void runHeartList() throws IOException {
		saveSeries();
	}

}

    