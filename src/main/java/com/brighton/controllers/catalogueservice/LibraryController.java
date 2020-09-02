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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/myapi")
public class LibraryController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String ctrlUrl = "http://localhost:5050/api";
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/addCollectorToLibrary")
	public HttpStatus addCollectorToLibrary(@RequestBody ObjectNode obj) {
		String url = ctrlUrl + "/addCollectorToLibrary";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(obj, getHeaders());
		HttpStatus status = null;
		try {
			status = restTemplate.postForObject(url, requestEntity, HttpStatus.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@PostMapping("/addFolderToLibrary")
	public <T> ResponseEntity<T> addFolderToLibrary(@RequestParam Long folderId) throws URISyntaxException {
		String url = ctrlUrl + "/addFolderToLibrary";
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(url).queryParam("folderId", folderId);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		ResponseEntity<T> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(urlBuilder.toUriString(), HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<T>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}
	 @GetMapping("/listLibrary")
	    public <T> ResponseEntity<List<T>> getAllLibrary() {
		 String url = ctrlUrl + "/listLibrary";
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
	 @GetMapping("/listLibraryTree")
	    public <T> ResponseEntity<List<T>> getLibraryTree() {
		 String url = ctrlUrl + "/listLibraryTree";
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
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
