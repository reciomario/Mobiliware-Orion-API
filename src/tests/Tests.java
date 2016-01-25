package tests;

import java.io.IOException;

import Utils.Constants;



import entities.Taxi;
import entities.Taxi_Desc;
import entities.Token;
import entities.User;

public class Tests {
	
	String user;
	String pass;
	Token token;
	
	public Tests(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	
	//Oauth Tests
	public void runOauthTests() throws IOException{
		System.out.println("------OAUTH TESTS------");
		//CREATE USER
		System.out.println("OAUTH TEST: Create user");
		User usuario = new User(user, pass);
		
		//GET FIWARE TOKEN
		System.out.println("OAUTH TEST: Request token");
		this.token = usuario.getSessionToken();
		
	}
	
	//Taxi Tests
	public void runTaxiTests() throws IOException{
		System.out.println("\n------TAXI TESTS------");
		
		String taxiIdExample = "00001";
		//UPLOAD A NEW TAXI
		System.out.println("TAXI TEST: Upload a taxi (ID=" + taxiIdExample + ")");
		Taxi taxiTest = new Taxi(taxiIdExample, "12/12/12", "-5.8447600", "43.3602900","54",
				Constants.TAXI_STATE_AVAILABLE);
		if(taxiTest.upload(token.getAccess_token())) System.out.println("Result: OK");
		else System.out.println("Result: ERROR");

		
		//GET A TAXI
		System.out.println("\nTAXI TEST: Get a taxi by ID (ID=" + taxiIdExample + ")");
		Taxi taxiGet = new Taxi();
		if(taxiGet.get(token.getAccess_token(), taxiIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Obtained Taxi information");
			System.out.println("\t ID = " + taxiGet.getTa_id());
			System.out.println("\t Date = " + taxiGet.getTa_date());
			System.out.println("\t Longitude = " + taxiGet.getTa_longitude());
			System.out.println("\t Latitude = " + taxiGet.getTa_latitude());
			System.out.println("\t Speed = " + taxiGet.getTa_speed());
			System.out.println("\t State = " + taxiGet.getTa_state());
			
		} else System.out.println("Result: ERROR");

		//DELETE
		System.out.println("\nTAXI TEST: Delete a taxi by ID (ID=" + taxiIdExample + ")");
		if(Taxi.deleteTaxi(token.getAccess_token(), taxiIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Deleted taxi ID:" + taxiGet.getTa_id());
		}
		else
			 System.out.println("Result: ERROR");
	}
	
	
	
	//Taxi Tests
	public void runTaxiDesTests() throws IOException{
		System.out.println("\n------TAXI DESCRIPTION TESTS------");
		
		String taxiDescIdExample = "00001D";
		//UPLOAD A NEW TAXI DESCRIPTION
		System.out.println("TAXI DESCRIPTION TEST: Upload a description taxi (ID=" + taxiDescIdExample + ")");
		Taxi_Desc taxiTest = new Taxi_Desc(taxiDescIdExample, "CDC9999", "53555304B", "4");
		if(taxiTest.upload(token.getAccess_token())) System.out.println("Result: OK");
		else System.out.println("Result: ERROR");

		
		//GET A TAXI
		System.out.println("\nTAXI DESCRIPTION TEST: Get a taxi description by ID (ID=" + taxiDescIdExample + ")");
		Taxi_Desc taxiGet = new Taxi_Desc();
		if(taxiGet.get(token.getAccess_token(), taxiDescIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Obtained Taxi Description information");
			System.out.println("\t ID = " + taxiGet.getTd_id());
			System.out.println("\t Plate = " + taxiGet.getTd_plate());
			System.out.println("\t License = " + taxiGet.getTd_license());
			System.out.println("\t Size = " + taxiGet.getTd_size());
			
		} else System.out.println("Result: ERROR");

		//DELETE
		System.out.println("\nTAXI DESCRIPTION TEST: Delete a taxi description by ID (ID=" + taxiDescIdExample + ")");
		if(Taxi.deleteTaxi(token.getAccess_token(), taxiDescIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Deleted taxi Description ID:" + taxiGet.getTd_id());
		}
		else
			 System.out.println("Result: ERROR");
	}

}
