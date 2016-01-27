
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import tests.Tests;
import Utils.Constants;
import Utils.Sender;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Entity;
import entities.Taxi;
import entities.Token;
import entities.User;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;


public class Main {

	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		loadCerts();
		
		Tests test = new Tests(Constants.OAUTH_USER_NAME, Constants.OAUTH_USER_PASS);
		test.runOauthTests();      	//testing Oauth (This tests are required for the rest of tests)
		
		
		test.runTaxiTests();			//testing Taxi
		test.runTaxiDesTests();			//testing Taxi_Desc
		test.runRequestStepsTests(); 	//Testing Request Step
		test.runRequestTests();			//testing Request

		

	}


	
	
	
	
	
	
	
	//LOAD SSL CERTIFICATES
	private static void loadCerts() throws NoSuchAlgorithmException, KeyManagementException {
		// Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
	}

	
}
