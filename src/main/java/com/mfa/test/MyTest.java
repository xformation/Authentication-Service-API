package com.mfa.test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.management.Notification;
import javax.management.Notification.*;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import com.google.zxing.WriterException;
public class MyTest {
		public static void main(String[] args) {
			GoogleAuthenticator gAuth = new GoogleAuthenticator();
			GoogleAuthenticatorKey key = gAuth.createCredentials();
			String keyUri = null;
			System.out.println(key.getKey());
			try {
//				keyUri= generateKeyUri("totp@example.com", "Vaadin TOTP",
//				        key.getKey());
				keyUri= generateKeyUri("totp@example.com", "Vaadin TOTP",
				        "2N4VAPDCX3COKCSVFXGAKKHSFFHP23KB");
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 System.out.println(keyUri);
			 String filePath = "D.png";
				int size = 125;
				String fileType = "png";
				File qrFile = new File(filePath);
			 try {
				GenerateQRCode.createQRImage(qrFile, keyUri, size, fileType);
			} catch (WriterException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		  private static String generateKeyUri(String account, String issuer,
		            String secret) throws URISyntaxException {

		        URI uri = new URI("otpauth", "totp", "/" + issuer + ":" + account,
		                "secret=" + secret + "&issuer=" + issuer, null);

		        return uri.toASCIIString();
		    }
}
