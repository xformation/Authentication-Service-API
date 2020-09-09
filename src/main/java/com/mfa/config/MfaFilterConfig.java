package com.mfa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mfa.filter.MfaAuthenticationFilter;

@Configuration
public class MfaFilterConfig {

//	 @Bean
//	 public FilterRegistrationBean  filterMfaAuthentication() {
//		  FilterRegistrationBean  registrationBean = new FilterRegistrationBean();
//		  MfaAuthenticationFilter mfaAuthenticationUrlFilter = new MfaAuthenticationFilter();
//		
//		  registrationBean.setFilter(mfaAuthenticationUrlFilter);
//		  registrationBean.addUrlPatterns("/mfaAuthentication");
////		  registrationBean.setOrder(2); //set precedence
//		  return registrationBean;
//	 }

	
}
