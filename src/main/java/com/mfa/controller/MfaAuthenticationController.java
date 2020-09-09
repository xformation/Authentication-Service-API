package com.mfa.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfa.MfaApplication;
import com.mfa.config.ApplicationProperties;
import com.mfa.config.Constants;
import com.warrenstrange.googleauth.GoogleAuthenticator;

@RestController
@CrossOrigin
public class MfaAuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(MfaAuthenticationController.class);
	
	@RequestMapping(value = "/mfaAuthentication")
	public void authenticateMfaToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final GoogleAuthenticator gAuth = new GoogleAuthenticator();
		ApplicationProperties ap = MfaApplication.getBean(ApplicationProperties.class);
		if(gAuth.authorize(ap.getGoogleAuthKey(), Integer.parseInt(request.getParameter("mfa")))) {
			long t = System.currentTimeMillis();
			Constants.USER_TOKEN.put(request.getParameter("mfa"),"");
			response.sendRedirect(ap.getClientUrl()+"?mfaPassToken="+request.getParameter("mfa")); 
			return;
		}
		
		response.sendRedirect(ap.getGoogleMfaUrl()+"?msg=Authentication code is invalid");
    }
	
	@RequestMapping(value = "/authenticateMfaToken")
	public ResponseEntity<Object> authenticateMfaToken(String userName, String secretKey, Integer mfaCode) throws IOException {
		final GoogleAuthenticator gAuth = new GoogleAuthenticator();
		ApplicationProperties ap = MfaApplication.getBean(ApplicationProperties.class);
		if(gAuth.authorize(secretKey, mfaCode)) {
			long t = System.currentTimeMillis();
			return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.value());
		}
		
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpStatus.EXPECTATION_FAILED.value());
    }
	
	@GetMapping("/api/validateToken")
    public ResponseEntity<Object> validateToken(@RequestParam String token) {
    	logger.info("Validating mfa token "+token);
    	try {
    		if(Constants.USER_TOKEN.containsKey(token)){
    			logger.debug("Token valid");
    			return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.value());
    		}
        }catch(Exception e) {
        	logger.error("Error in token validation: ",e);
        	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpStatus.EXPECTATION_FAILED.value());
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpStatus.EXPECTATION_FAILED.value());
    }
    
    @GetMapping("/api/removeToken")
    public ResponseEntity<Object> removeToken(@RequestParam String token) {
    	logger.info("Removing mfa token "+token);
    	try {
    		if(Constants.USER_TOKEN.containsKey(token)){
    			Constants.USER_TOKEN.remove(token);
    			logger.debug("Token deleted");
    			return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.value());
    		}
        }catch(Exception e) {
        	logger.error("Error in token validation: ",e);
        	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpStatus.EXPECTATION_FAILED.value());
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpStatus.EXPECTATION_FAILED.value());
    }
    
}
