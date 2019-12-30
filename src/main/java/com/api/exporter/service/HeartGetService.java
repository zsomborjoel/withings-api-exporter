package com.api.exporter.service;

import java.io.IOException;
import java.util.List;

import com.api.exporter.model.ApplicationProperties;
import com.api.exporter.model.HeartGetApi.GetBody;
import com.api.exporter.model.HeartGetApi.GetResponse;
import com.api.exporter.repository.HeartGetRepository;

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
	
	@Autowired
	private HeartListService heartListService;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Autowired
    private RestTemplate restTemplate;

    @Value("${withings.api.host}")
	private String apiHost;
	
	/**
	 * Define url based on host, signalid, token
	 * @param signalId
	 * @return
	 */
	public String makeUrl(Integer signalId) {
		String url = apiHost + "/heart?action=get" + 
					"&signalid=" + String.valueOf(signalId) +
					"&access_token=" + applicationProperties.getAccessToken();

		return url;
	}

	/**
	 * Gives back whole API response as an object
	 * @param signalId
	 * @return
	 * @throws IOException
	 */
    public GetResponse getResponse(Integer signalId) throws IOException {
		String url = makeUrl(signalId);

		return restTemplate.getForObject(url, GetResponse.class);
	}
  
	/**
	 * Gives back API response Body as an object
	 * @param signalId
	 * @return
	 * @throws IOException
	 */
	public GetBody getBodyData(Integer signalId) throws IOException {
        GetResponse getResponse = getResponse(signalId);
        
		return getResponse.getBody();
	}
	
	/**
	 * Persist Body data to database
	 * @param signalId
	 * @throws IOException
	 */
	private void saveBody(Integer signalId) throws IOException {
		GetBody getBody = getBodyData(signalId);
		
		heartGetRepository.save(getBody);
	}

	/**
	 * Starts Withings HeartGet API persisting mechanism
	 * @throws IOException
	 */
	public void runHeartGet() throws IOException {
		List<Integer> signalIds = heartListService.getSignalIds();
		for (Integer signalId : signalIds) {
			saveBody(signalId);
		}
		logger.info("HeartGet Data load finished");
	}

}