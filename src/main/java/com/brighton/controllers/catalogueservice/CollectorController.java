package com.brighton.controllers.catalogueservice;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
public class CollectorController {
	private String ctrlUrl = "http://localhost:5050/api";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/addCollector")
	public <T> ResponseEntity<List<T>> addCollector(@RequestParam String name, @RequestParam String type,
			@RequestParam(required = false) String description) throws URISyntaxException {
		String url = ctrlUrl + "/addCollector";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("name", name)
				.queryParam("type", type).queryParam("description", description);

		ResponseEntity<List<T>> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<List<T>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	@PutMapping("/updateCollector")
	public <T> ResponseEntity<T> updateCollector(@RequestParam Long id, @RequestParam String dataSource)
			throws URISyntaxException {
		String url = ctrlUrl + "/updateCollector";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("id", id)
				.queryParam("dataSource", dataSource);

		ResponseEntity<T> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, requestEntity,
					new ParameterizedTypeReference<T>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	@DeleteMapping("/deleteCollector/{id}")
	public ResponseEntity<Void> deleteCollector(@PathVariable Long id) {
		String url = ctrlUrl + "/deleteCollector/{id}";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());

		ResponseEntity<Void> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@GetMapping("/listCollector")
	public <T> ResponseEntity<List<T>> getAllCollectors() {
		String url = ctrlUrl + "/listCollector";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		ResponseEntity<List<T>> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<List<T>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@GetMapping("/getCollector/{id}")
	public <T> ResponseEntity<T> getCollector(@PathVariable Long id) throws URISyntaxException {
		String url = ctrlUrl + "/getCollector/{id}";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());

		List<T> list = null;
		ResponseEntity<T> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<T>() {
					}, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@GetMapping("/searchCollector")
	public <T> ResponseEntity<List<T>> searchCollector(@RequestParam Map<String, String> criteriaMap) {
		String url = ctrlUrl + "/searchCollector";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());

		List<T> list = null;
		ResponseEntity<List<T>> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<List<T>>() {
					});
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
