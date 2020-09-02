package com.brighton.test;

import com.warrenstrange.googleauth.GoogleAuthenticator;

public class Validate {
		public static void main(String[] args) {
			  final GoogleAuthenticator gAuth = new GoogleAuthenticator();
			  boolean matches = gAuth.authorize("LDGPNSZMHRMFJN5OTUE5BH2IJCIUCKU6", 945370);
			System.out.println(matches);
		}
}
