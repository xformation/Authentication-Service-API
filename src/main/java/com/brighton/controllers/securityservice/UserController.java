package com.brighton.controllers.securityservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/security/users/")
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	private String ctrlUrl="http://localhost:8094/security/users";
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(path = "/listAll", method = RequestMethod.GET)
	public ResponseEntity<Object> findAll(HttpServletRequest request) {
		String url = ctrlUrl + "/listAll";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(request, getHeaders());
		//ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
//		Object responseEntity=restTemplate.postForObject(url, request, Object.class);
		ResponseEntity<Object> responseEntity=restTemplate.getForEntity(url, Object.class);
		return responseEntity;
	}
	@RequestMapping("/create")
	public ResponseEntity<Object> create(@RequestBody ObjectNode service,HttpServletRequest request) {
		String url=ctrlUrl+"/create";
		/*
		 * MultiValueMap<String, Object> multiValueMap=new LinkedMultiValueMap<String,
		 * Object>(); multiValueMap.set("service", service);
		 * multiValueMap.set("request", request);
		 */
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(service, getHeaders());
		ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
		return responseEntity;
	}
	@RequestMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") String id) {
		String url=ctrlUrl+"/{id}";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class,id);
		return responseEntity;
		
	}
	@RequestMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable("id") String id,HttpServletRequest request) {
		String url=ctrlUrl+"/delete/{id}";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(getHeaders());
		//ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class,id);
		ResponseEntity responseEntity = null;
		try {
			responseEntity = restTemplate.postForEntity(url, requestEntity, String.class,id);
			
		}catch (Throwable th) {
			logger.error(th.getMessage(), th);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(th);
		}
		return responseEntity;
	
	}
	@RequestMapping("/update")
	public ResponseEntity<Object> update(@RequestBody ObjectNode entity,HttpServletRequest request) {
		String url=ctrlUrl+"/update";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(entity,getHeaders());
		ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
		return responseEntity;
	}
	@RequestMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody ObjectNode entity) {
		String url=ctrlUrl+"/delete";
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(entity,getHeaders());
		ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		return responseEntity;
	}
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
