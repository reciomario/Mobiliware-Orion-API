package Utils;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class Sender {

	
	/** This method obtains the authentication response (String) given a user/pass
	 * 
	 * @param authUrl
	 *		URL of the Fiware Authentication Server
	 * @param name
	 * 		Name of the user to authenticate
	 * @param pass
	 * 		Password of the user to authenticate
	 * @return		String with the response
	 * @throws IOException
	 */
	public static String getAuth(String authUrl, String name, String pass) throws IOException{ 
		
		String query = "grant_type=password&username=" + name + 
				"&password=" + pass;
		URL myurl = new URL(authUrl);
		
		HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-length", String.valueOf(query.length())); 
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
		con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)"); 
		con.setDoOutput(true); 
		con.setDoInput(true); 
		if(Constants.DEBUG) System.out.println(authUrl);
		DataOutputStream output = new DataOutputStream(con.getOutputStream());  
		output.writeBytes(query);
		output.close();
		DataInputStream input = new DataInputStream( con.getInputStream() ); 

		String response = "";
		for( int c = input.read(); c != -1; c = input.read() ) 
		response = response +  (char)c ; 
		input.close(); 
		if(Constants.DEBUG) {
			System.out.println("Resp Code:"+con.getResponseCode() + "\t" + 
				"Resp Message:"+ con.getResponseMessage()); 
			System.out.println("Resp: " + response);
		}
		return response;
	}
	
	
	
	/**
	 * Sends a petition to the Url defined in restUrl, with the method defined in method and with
	 * the authToken attached
	 * 
	 * @param authToken
	 * @param restUrl
	 * @param method
	 * @return
	 * @throws IOException
	 */
	public static String send(String authToken, String restUrl, String method) throws IOException{ 
	
		URL url = new URL(restUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("X-Auth-Token",authToken); 
		if(Constants.DEBUG) System.out.println("URL-" + url.toString());

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		DataInputStream input = new DataInputStream( conn.getInputStream() ); 

		String response = "";
		for( int c = input.read(); c != -1; c = input.read() ) 
		response = response +  (char)c ; 
		input.close(); 
		if(Constants.DEBUG) {
			System.out.println("Resp Code:"+conn.getResponseCode() + "\t" + 
				"Resp Message:"+ conn.getResponseMessage()); 
			System.out.println("Resp: " + response);
		}
		return response;
	}
	
	
	public static String send(String authToken, String restUrl, String method, String jsonAttached) 
			throws IOException{ 
		
		URL url = new URL(restUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-length", String.valueOf(jsonAttached.length())); 
		conn.setRequestProperty("Content-Type", "application/json"); 
		conn.setRequestProperty("Accept", "application/json"); 
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)"); 
		conn.setRequestProperty("X-Auth-Token",authToken); 
		

		if(Constants.DEBUG) System.out.println("URL-" + url.toString());
		conn.setDoOutput(true); 
		conn.setDoInput(true); 
		if(!method.equals("DELETE")){
			DataOutputStream output = new DataOutputStream(conn.getOutputStream());  
			output.writeBytes(jsonAttached);
			output.close();
		}
		DataInputStream input = new DataInputStream(conn.getInputStream() ); 

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}


		String response = "";
		for( int c = input.read(); c != -1; c = input.read() ) 
			response = response +  (char)c ; 
		input.close(); 
		if(Constants.DEBUG) {
			System.out.println("Resp Code:"+conn.getResponseCode() + "\t" + 
				"Resp Message:"+ conn.getResponseMessage()); 
			System.out.println("Resp: " + response);
		}
		return response;
	}
}

