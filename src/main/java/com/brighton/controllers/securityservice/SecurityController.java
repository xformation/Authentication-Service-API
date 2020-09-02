package com.brighton.controllers.securityservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import liquibase.pro.packaged.bu;

@RestController
@RequestMapping("/security/public")
public class SecurityController {
	private String ctrlUrl="http://localhost:8094/security/public";
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ResponseEntity<Object> login(@RequestParam  String username, @RequestParam String password,
				@RequestParam(required = false) boolean rememberMe) {
					String url=ctrlUrl+"/login";
					
					UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(url).queryParam("username",username)
							.queryParam("password", password)
							.queryParam("rememberMe", rememberMe);
//					System.out.println(builder.toUriString());
					//HttpEntity<String> requestEntity=new HttpEntity<String>(getHeaders());
					//ResponseEntity<Object> 	responseEntity=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, Object.class);
					//return responseEntity;
					Object status = null;
					status = restTemplate.getForObject(builder.toUriString(), Object.class);
					return ResponseEntity.status(HttpStatus.OK).body(status);
			
		}
	@RequestMapping(value = "/signup")
	public String login(@RequestBody Object request) {
		String url=ctrlUrl+"/signup";
		HttpEntity<Object> requestEntity=new HttpEntity<Object>(request,getHeaders());
		String resp=restTemplate.postForObject(url, requestEntity, String.class);
		return resp;
		
	}
	@RequestMapping(value = "/singin")
	public ResponseEntity<Object> singin(@RequestBody Object user) {
		String url=ctrlUrl+"/singin";
		HttpEntity<Object> requestEntity=new HttpEntity<Object>(user,getHeaders());
		ResponseEntity<Object> responseEntity=restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
		return responseEntity;
		
	}
	@RequestMapping(value = "/authenticate")
	@ResponseBody
	public ResponseEntity<Object> authenticate(@RequestBody final Object token) {
		String url=ctrlUrl+"/authenticate";
		HttpEntity<Object> requestEntity=new HttpEntity<Object>(token,getHeaders());
		ResponseEntity<Object> responseEntity=restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
		return responseEntity;
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		String url=ctrlUrl+"/logout";
		String response=restTemplate.getForObject(url, String.class);
		return response;

	}
	@RequestMapping(value = "/importUser")
	public ResponseEntity<Object> importUser(@RequestBody List<String> list) {
		String url=ctrlUrl+"/importUser";
		HttpEntity<Object> requestEntity=new HttpEntity<Object>(list,getHeaders());
		ResponseEntity<Object> responseEntity=restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
		return responseEntity;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
