package com.brighton.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.warrenstrange.googleauth.GoogleAuthenticator;

@Component
public class MyFilter  implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter called");
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
//		System.out.println("Requested URL="+httpServletRequest.getRequestURI());
		if(httpServletRequest.getRequestURI().equalsIgnoreCase("/mfaAuthentication")) {
			final GoogleAuthenticator gAuth = new GoogleAuthenticator();
			if(gAuth.authorize("2N4VAPDCX3COKCSVFXGAKKHSFFHP23KB", Integer.parseInt(request.getParameter("mfa")))) {
			httpServletResponse.sendRedirect("http://localhost:3000/login"); 
				return;
			}else {
				httpServletResponse.sendRedirect("http://localhost:4040/MFAPage.html?msg=Authentication code is invalid");
				//request.getRequestDispatcher("/MFAPage.html?msg=authentication code is invalid").forward(request, response);
				return;
			}
		}
		request.getRequestDispatcher("/MFAPage.html").forward(request, response);
		
	}

}
