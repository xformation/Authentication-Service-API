package com.brighton.controllers.catalogueservice;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/myapi")
public class DashboardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String ctrlUrl = "http://localhost:5050/api";
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/addDashboardToCollector")
	public <T> ResponseEntity<T> addDashboardToCollector(@RequestParam Long collectorId,
			@RequestParam String dashboardName, @RequestParam String dashboardJson,
			@RequestParam(required = false) String dashboardDoc) throws URISyntaxException {
		String url = ctrlUrl + "/addDashboardToCollector";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("collectorId", collectorId)
				.queryParam("dashboardName", dashboardName).queryParam("dashboardJson", dashboardJson)
				.queryParam("dashboardDoc", dashboardDoc);
		ResponseEntity<T> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<T>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@DeleteMapping("/deleteDashboardFromCollector/{id}")
	public ResponseEntity<Void> deleteDashboardFromCollector(@PathVariable Long id) {
		String url = ctrlUrl + "/deleteDashboardFromCollector/{id}";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());

		ResponseEntity<Void> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@GetMapping("/listDashboardOfCollector/{collectorId}")
	public <T> ResponseEntity<List<T>> listDashboardOfCollector(@PathVariable Long collectorId) {
		String url = ctrlUrl + "/listDashboardOfCollector/{collectorId}";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		ResponseEntity<List<T>> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<List<T>>() {
					}, collectorId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@GetMapping("/listDashboard")
	public <T> ResponseEntity<List<T>> listAllDashboard(@RequestParam(required = false) Long id,
			@RequestParam(required = false) String isFolder) {
		String url = ctrlUrl + "/listDashboard";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(url).queryParam("id", id)
				.queryParam("isFolder", isFolder);
		ResponseEntity<List<T>> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(urlBuilder.toUriString(), HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<List<T>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	@GetMapping("/getDashboard/{id}")
	public <T> ResponseEntity<T> getDashboard(@PathVariable Long id) throws URISyntaxException {
		String url = ctrlUrl + "/getDashboard/{id}";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		ResponseEntity<T> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<T>() {
					},id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
