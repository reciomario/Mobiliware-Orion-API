package entities;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.invoke.ConstantCallSite;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import Utils.Constants;
import Utils.Sender;

import com.fasterxml.jackson.databind.ObjectMapper;

public class User {

	
	public String name;
	public String pass;
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public User(String name, String pass){
		this.name = name;
		this.pass = pass;
	}
	
	public Token getSessionToken() throws IOException {		

			String response = Sender.getAuth(Constants.AUTH_URL, name, pass);
		
			//JSON from String to Token
			ObjectMapper mapper = new ObjectMapper();
			try {						//Token has been sent. AUTH is OK
				return mapper.readValue(response, Token.class);
			} catch (IOException e) {	//Token has NOT been sent. AUTH PROBLEM
				System.out.println("AUTHENTICATION ERROR - " + response);
				e.printStackTrace();
				throw new java.io.IOException();
			}

}
}
