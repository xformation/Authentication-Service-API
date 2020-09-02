package com.brighton.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityServiceController {
	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}

}
