package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.Constants;



import entities.Request;
import entities.Request_Step;
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
		
		String taxiIdExample = "T00001";
		//UPLOAD 
		System.out.println("TAXI TEST: Upload a taxi (ID=" + taxiIdExample + ")");
		Taxi taxiTest = new Taxi(taxiIdExample, "12/12/12", "-5.8447600", "43.3602900","54",
				Constants.TAXI_STATE_AVAILABLE);
		if(taxiTest.upload(token.getAccess_token())) System.out.println("Result: OK");
		else System.out.println("Result: ERROR");

		
		//GET 
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
		if(Taxi.delete(token.getAccess_token(), taxiIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Deleted taxi ID:" + taxiGet.getTa_id());
		}
		else
			 System.out.println("Result: ERROR");
	}
	
	
	
	
	//Taxi Description Tests
	public void runTaxiDesTests() throws IOException{
		System.out.println("\n------TAXI DESCRIPTION TESTS------");
		
		String taxiDescIdExample = "T00001D";
		//UPLOAD 
		System.out.println("TAXI DESCRIPTION TEST: Upload a description taxi (ID=" + taxiDescIdExample + ")");
		Taxi_Desc taxiDescTest = new Taxi_Desc(taxiDescIdExample, "CDC9999", "53555304B", "4");
		if(taxiDescTest.upload(token.getAccess_token())) System.out.println("Result: OK");
		else System.out.println("Result: ERROR");

		
		//GET 
		System.out.println("\nTAXI DESCRIPTION TEST: Get a taxi description by ID (ID=" + taxiDescIdExample + ")");
		Taxi_Desc taxiDescGet = new Taxi_Desc();
		if(taxiDescGet.get(token.getAccess_token(), taxiDescIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Obtained Taxi Description information");
			System.out.println("\t ID = " + taxiDescGet.getTd_id());
			System.out.println("\t Plate = " + taxiDescGet.getTd_plate());
			System.out.println("\t License = " + taxiDescGet.getTd_license());
			System.out.println("\t Size = " + taxiDescGet.getTd_size());
			
		} else System.out.println("Result: ERROR");

		//DELETE
		System.out.println("\nTAXI DESCRIPTION TEST: Delete a taxi description by ID (ID=" + taxiDescIdExample + ")");
		if(Taxi.delete(token.getAccess_token(), taxiDescIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Deleted taxi Description ID:" + taxiDescGet.getTd_id());
		}
		else
			 System.out.println("Result: ERROR");
	}

	//Request_Step Tests
	public void runRequestStepsTests() throws IOException {
		System.out.println("\n------REQUEST STEP TESTS------");
		
		String requestStepIdExample = "R00001S001";
		//UPLOAD
		System.out.println("REQUEST STEP TEST: Upload a Request Step (ID=" + requestStepIdExample + ")");
		Request_Step requestStepTest = new Request_Step(requestStepIdExample,
				"W00001", "2016/01/30 11:59:59", 
				"W00002", "2016/01/30 12:59:59", "60");
		if(requestStepTest.upload(token.getAccess_token())) System.out.println("Result: OK");
		else System.out.println("Result: ERROR");

		
		//GET
		System.out.println("\nREQUEST STEP TEST: Get a Request Step by ID (ID=" + requestStepIdExample + ")");
		Request_Step requestStepGet = new Request_Step();
		if(requestStepGet.get(token.getAccess_token(), requestStepIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Obtained Request information");
			System.out.println("\t ID = " + requestStepGet.getRs_id());
			System.out.println("\t Start Waypoint = " +  requestStepGet.getRs_startWaypointId());
			System.out.println("\t Start Time = " +  requestStepGet.getRs_startTime());
			System.out.println("\t End Waypoint = " +  requestStepGet.getRs_endWaypointId());
			System.out.println("\t End Time = " +  requestStepGet.getRs_endTime());
			System.out.println("\t Total Time = " +  requestStepGet.getRs_totalTime());

			
		} else System.out.println("Result: ERROR");

		//DELETE
		System.out.println("\nREQUEST STEP TEST: Delete a Request Step by ID (ID=" + requestStepIdExample + ")");
		if(Request.delete(token.getAccess_token(), requestStepIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Deleted Request ID:" + requestStepGet.getRs_id());
		}
		else
			 System.out.println("Result: ERROR");
		
	}
		
		
	
	
	//Request Tests
	public void runRequestTests() throws IOException {
		System.out.println("\n------REQUEST TESTS------");
		String requestIdExample = "R00001";
		
		//Request_Steps Array creation
		List<Request_Step> requestStepTest = new ArrayList<Request_Step>();
		requestStepTest.add(new Request_Step(requestIdExample + "S000",
				"W00001", "2016/01/30 11:59:59", 
				"W00002", "2016/01/30 12:59:59", "20"));
		requestStepTest.add(new Request_Step(requestIdExample + "S001",
				"W00002", "2016/01/30 12:19:59", 
				"W00003", "2016/01/30 12:49:59", "30"));
		requestStepTest.add(new Request_Step(requestIdExample + "S002",
				"W00003", "2016/01/30 12:49:59", 
				"W00004", "2016/01/30 12:59:59", "10"));

		//UPLOAD A NEW REQUEST
		System.out.println("REQUEST TEST: Upload a Request (ID=" + requestIdExample + ")");
		Request requestTest = new Request(requestIdExample, "W00001", "2016/01/30 11:59:59", 
				"W00004", "2016/01/30 12:59:59", "60", "12.50",
				"3", "PLANNED", requestStepTest);
		if(requestTest.upload(token.getAccess_token())) System.out.println("Result: OK");
		else System.out.println("Result: ERROR");

		
		//GET A REQUEST
		System.out.println("\nREQUEST TEST: Get a Request by ID (ID=" + requestIdExample + ")");
		Request requestGet = new Request();
		if(requestGet.get(token.getAccess_token(), requestIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Obtained Request information");
			System.out.println("\t ID = " + requestGet.getRe_id());
			System.out.println("\t Start Waypoint = " +  requestGet.getRe_startWaypointId());
			System.out.println("\t Start Time = " +  requestGet.getRe_startTime());
			System.out.println("\t End Waypoint = " +  requestGet.getRe_endWaypointId());
			System.out.println("\t End Time = " +  requestGet.getRe_endTime());
			System.out.println("\t Total Time = " +  requestGet.getRe_totalTime());
			System.out.println("\t Total Cost = " +  requestGet.getRe_totalCost());
			System.out.println("\t Number of Steps = " +  requestGet.getRe_numSteps());
			System.out.println("\t State = " +  requestGet.getRe_state());

			for (Request_Step requestStep : requestGet.getRe_requestSteps()) {
				System.out.println("\t Request Steps:" );
				System.out.println("\t\t ID = " + requestStep.getRs_id() );
				System.out.println("\t\t Waypoints = FROM " + requestStep.getRs_startWaypointId() 
						+ " TO " + requestStep.getRs_endWaypointId());
				System.out.println("\t\t Times = FROM " + requestStep.getRs_startTime() 
						+ " TO " + requestStep.getRs_endTime());
				System.out.println("\t\t Total time = " + requestStep.getRs_totalTime());	
			}
			
		} else System.out.println("Result: ERROR");

		//DELETE
		System.out.println("\nREQUEST TEST: Delete a Request by ID (ID=" + requestIdExample + ")");
		if(Request.delete(token.getAccess_token(), requestIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Deleted Request ID:" + requestGet.getRe_id());
		}
		else
			 System.out.println("Result: ERROR");
		
	}
	
	
	
	
	
	
	
	
}
