package com.api.exporter.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

import com.api.exporter.model.ApplicationProperties;
import com.api.exporter.model.HeartListApi.ListResponse;
import com.api.exporter.model.HeartListApi.Series;
import com.api.exporter.repository.HeartListRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeartListService {

	private static final Logger logger = LoggerFactory.getLogger(HeartListService.class);

	private final LocalDate now = LocalDate.now();

	private final LocalDate previousDay = now.minusDays(1);
	
    @Autowired
	private HeartListRepository hlRepository;

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${withings.api.host}")
	private String apiHost;

	/**
	 * Converts date to unixtimestamp for API call
	 * @param date
	 * @return
	 */
	public long dateToEpoch(LocalDate date) {
		ZoneId zoneId = ZoneId.systemDefault();

        return date.atStartOfDay(zoneId).toEpochSecond();
	}

	/**
	 * Define url based on host, startdate, enddate, token
	 * @return
	 */
	public String makeUrl() {
		String url = apiHost + "/heart?action=list" + 
					"&startdate=" + dateToEpoch(previousDay) +
					"&enddate=" + dateToEpoch(now) +
					"&access_token=" + applicationProperties.getAccessToken();

		logger.info(url);

		return url;
	}

	/**
	 * Gives back whole API response as an object
	 * @return
	 * @throws IOException
	 */
	public ListResponse getResponse() throws IOException {
		String url = makeUrl();

		return restTemplate.getForObject(url, ListResponse.class);

	}
  
	/**
	 * Gives back API response Body as an object
	 * @return
	 * @throws IOException
	 */
	public List<Series> getSeriesData() throws IOException {
		ListResponse listResponse = getResponse();

		return listResponse.getBody().getSeries();
	}

	/**
	 * Gives back signalIds for HeartGet API call
	 * @return
	 * @throws IOException
	 */
	public List<Integer> getSignalIds() throws IOException {
		List<Integer> signalIds = new ArrayList<>();
		List<Series> series = getSeriesData();
		series.forEach(e -> signalIds.add(e.getEcg().getSignalid()));
		
		return signalIds;
	}
	
	/**
	 * Persist Series data
	 * @throws IOException
	 */
	private void saveSeries() throws IOException {
		List<Series> series = getSeriesData();
		series.forEach(s -> {
			s.setEnddate(now); 
			s.setStartdate(previousDay);
						});

		hlRepository.saveAll(series);
	}

	/**
	 * Starts Withings HeartList API persisting mechanism
	 * 
	 */
	public void runHeartList() {
		try {
			logger.info("HeartList Data load started");
			saveSeries();
			logger.info("HeartList Data load finished");
		} catch(Exception e) {
			logger.error("Exception occured during HeartList load ", e);
		}
	}

}

    