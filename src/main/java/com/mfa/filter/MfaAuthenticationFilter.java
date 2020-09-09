package com.mfa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfa.MfaApplication;
import com.mfa.config.ApplicationProperties;
import com.mfa.config.Constants;
import com.warrenstrange.googleauth.GoogleAuthenticator;

public class MfaAuthenticationFilter {//implements Filter{

//	private static final Logger logger = LoggerFactory.getLogger(MfaAuthenticationFilter.class);
//
//	@Override
//	 public void init(FilterConfig filterConfig) throws ServletException {
//		logger.info("########## Initiating MfaAuthenticationFilter ##########");
//	 }
//
//	 @Override
//	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//		logger.info("This Filter is only called when request is mapped for /mfaAuthentication resource");
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		ApplicationProperties ap = MfaApplication.getBean(ApplicationProperties.class);
//		
////		if(request.getRequestURI().equalsIgnoreCase("/mfaAuthentication")) {
//			final GoogleAuthenticator gAuth = new GoogleAuthenticator();
//			if(gAuth.authorize(ap.getGoogleAuthKey(), Integer.parseInt(request.getParameter("mfa")))) {
//				long t = System.currentTimeMillis();
//				Constants.USER_TOKEN.put(request.getParameter("mfa"),"");
//				response.sendRedirect(ap.getClientUrl()+"?mfaPassToken="+request.getParameter("mfa")); 
//				return;
//			}else {
//				response.sendRedirect(ap.getGoogleMfaUrl()+"?msg=Authentication code is invalid");
//				//request.getRequestDispatcher("/MFAPage.html?msg=authentication code is invalid").forward(request, response);
//				return;
//			}
////		}
//		
//		//call next filter in the filter chain
////		filterChain.doFilter(request, response);
//	 }
//
//	 @Override
//	 public void destroy() {
//
//	 }
	 
}
