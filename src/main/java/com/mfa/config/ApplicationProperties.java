package com.mfa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
	private String googleAuthKey;
	private String googleMfaUrl;
	private String clientUrl;
	
	public String getGoogleAuthKey() {
		return googleAuthKey;
	}
	public void setGoogleAuthKey(String googleAuthKey) {
		this.googleAuthKey = googleAuthKey;
	}
	public String getGoogleMfaUrl() {
		return googleMfaUrl;
	}
	public void setGoogleMfaUrl(String googleMfaUrl) {
		this.googleMfaUrl = googleMfaUrl;
	}
	public String getClientUrl() {
		return clientUrl;
	}
	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}
}
