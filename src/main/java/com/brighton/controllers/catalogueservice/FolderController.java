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
public class FolderController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String ctrlUrl = "http://localhost:5050/api";
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/addFolder")
	public <T> ResponseEntity<List<T>> addFolder(@RequestParam String title,
			@RequestParam(name = "parentId", required = false) Long parentId) throws URISyntaxException {
		String url = ctrlUrl + "/addFolder";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(url).queryParam("title", title)
				.queryParam("parentId", parentId);
		ResponseEntity<List<T>> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(urlBuilder.toUriString(), HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<List<T>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}
	@GetMapping("/listFolder")
    public<T> ResponseEntity<List<T>> getAllFolders() {
		String url = ctrlUrl + "/listFolder";
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
	@GetMapping("/listFolderTree")
    public <T> ResponseEntity<List<T>> getFoldersTree() {
		String url = ctrlUrl + "/listFolderTree";
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
	@GetMapping("/listCollectorOfFolder/{folder}")
    public <T> ResponseEntity<List<T>> listCollectorOfFolder(@PathVariable String folder) {
		String url = ctrlUrl + "/listCollectorOfFolder/{folder}";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		ResponseEntity<List<T>> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<List<T>>() {
					},folder);
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
