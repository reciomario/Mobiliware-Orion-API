package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.Constants;



import entities.Passenger;
import entities.Request;
import entities.Request_Step;
import entities.Taxi;
import entities.Taxi_Desc;
import entities.Token;
import entities.User;
import entities.Waypoint;

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
		System.out.println("Idm: " + Constants.AUTH_URL);
		//CREATE USER
		System.out.println("OAUTH TEST: Create user");
		User usuario = new User(user, pass);
		System.out.println("Result: OK");
		
		//GET FIWARE TOKEN
		System.out.println("OAUTH TEST: Request token");
		this.token = usuario.getSessionToken();
		System.out.println("Result: OK");
		
	}
	
	//Taxi Tests
	public void runTaxiTests() throws IOException{
		System.out.println("\n------TAXI TESTS------");
		
		String taxiIdExample = "T00002";
		//UPLOAD 
		System.out.println("TAXI TEST: Upload a taxi (ID=" + taxiIdExample + ")");
		Taxi taxiTest = new Taxi(taxiIdExample, "12/12/12", "-5.8447600", "43.3612900","54",
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

//		//DELETE
//		System.out.println("\nTAXI TEST: Delete a taxi by ID (ID=" + taxiIdExample + ")");
//		if(Taxi.delete(token.getAccess_token(), taxiIdExample)) {
//			System.out.println("Result: OK");
//			System.out.println("\t Deleted taxi ID:" + taxiGet.getTa_id());
//		}
//		else
//			 System.out.println("Result: ERROR");
		


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

//		//DELETE
//		System.out.println("\nTAXI DESCRIPTION TEST: Delete a taxi description by ID (ID=" + taxiDescIdExample + ")");
//		if(Taxi.delete(token.getAccess_token(), taxiDescIdExample)) {
//			System.out.println("Result: OK");
//			System.out.println("\t Deleted taxi Description ID:" + taxiDescGet.getTd_id());
//		}
//		else
//			 System.out.println("Result: ERROR");
	}

	//Request_Step Tests
	public void runRequestStepsTests() throws IOException {
		System.out.println("\n------REQUEST STEP TESTS------");
		
		String requestStepIdExample = "R00001S001";
		//UPLOAD
		System.out.println("REQUEST STEP TEST: Upload a Request Step (ID=" + requestStepIdExample + ")");
		Request_Step requestStepTest = new Request_Step(requestStepIdExample,
				"T00001",
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

//		//DELETE
//		System.out.println("\nREQUEST STEP TEST: Delete a Request Step by ID (ID=" + requestStepIdExample + ")");
//		if(Request.delete(token.getAccess_token(), requestStepIdExample)) {
//			System.out.println("Result: OK");
//			System.out.println("\t Deleted Request ID:" + requestStepGet.getRs_id());
//		}
//		else
//			 System.out.println("Result: ERROR");
		
	}
		
		
	
	
	//Request Tests
	public void runRequestTests() throws IOException {
		System.out.println("\n------REQUEST TESTS------");
		String requestIdExample = "R00001";
		
		//Request_Steps Array creation
		List<Request_Step> requestStepTest = new ArrayList<Request_Step>();
		requestStepTest.add(new Request_Step(requestIdExample + "S000",
				"T00001",
				"W00001", "2016/01/30 11:59:59", 
				"W00002", "2016/01/30 12:59:59", "20"));
		requestStepTest.add(new Request_Step(requestIdExample + "S001",
				"T00001",
				"W00002", "2016/01/30 12:19:59", 
				"W00003", "2016/01/30 12:49:59", "30"));
		requestStepTest.add(new Request_Step(requestIdExample + "S002",
				"T00001",
				"W00003", "2016/01/30 12:49:59", 
				"W00004", "2016/01/30 12:59:59", "10"));

		//UPLOAD A NEW REQUEST
		System.out.println("REQUEST TEST: Upload a Request (ID=" + requestIdExample + ")");
		Request requestTest = new Request(requestIdExample, "P00001", "W00001", "2016/01/30 11:59:59", 
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
			System.out.println("\t Passenger Id = " +  requestGet.getPa_id());
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
				System.out.println("\t\t Taxi ID = " + requestStep.getTa_id() );
				System.out.println("\t\t Waypoints = FROM " + requestStep.getRs_startWaypointId() 
						+ " TO " + requestStep.getRs_endWaypointId());
				System.out.println("\t\t Times = FROM " + requestStep.getRs_startTime() 
						+ " TO " + requestStep.getRs_endTime());
				System.out.println("\t\t Total time = " + requestStep.getRs_totalTime());	
			}
			
		} else System.out.println("Result: ERROR");

//		//DELETE
//		System.out.println("\nREQUEST TEST: Delete a Request by ID (ID=" + requestIdExample + ")");
//		if(Request.delete(token.getAccess_token(), requestIdExample)) {
//			System.out.println("Result: OK");
//			System.out.println("\t Deleted Request ID:" + requestGet.getRe_id());
//		}
//		else
//			 System.out.println("Result: ERROR");
		
	}
	
	public void deleting() throws IOException {
		Taxi taxiBorrador = new Taxi();
		taxiBorrador.delete(token.getAccess_token(), "P2");
		taxiBorrador.delete(token.getAccess_token(), "P1");
	}
	
	
	
	//Passenger Tests
	public void runPassengerTests() throws IOException {
		System.out.println("\n------PASSENGER TESTS------");
		
		String passengerIdExample = "P00001";
		//UPLOAD
		System.out.println("PASSENGGER TEST: Upload a Passenger(ID=" + passengerIdExample + ")");
		Passenger passengerTest = new Passenger(passengerIdExample,
				"examplemail@example.com");
		if(passengerTest.upload(token.getAccess_token())) System.out.println("Result: OK");
		else System.out.println("Result: ERROR");

		
		//GET
		System.out.println("\nPASSENGGER TEST: Get a Passenger by ID (ID=" + passengerIdExample + ")");
		Passenger passengerGet = new Passenger();
		if(passengerGet.get(token.getAccess_token(), passengerIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Obtained Passenger information");
			System.out.println("\t ID = " + passengerGet.getPa_id());
			System.out.println("\t Email = " +  passengerGet.getPa_email());
			
		} else System.out.println("Result: ERROR");

//			//DELETE
//			System.out.println("\nPASSENGER TEST: Delete a Passenger by ID (ID=" + passengerIdExample + ")");
//			if(Request.delete(token.getAccess_token(), passengerIdExample)) {
//				System.out.println("Result: OK");
//				System.out.println("\t Deleted Passenger ID:" + passengerGet.getPa_id());
//			}
//			else
//				 System.out.println("Result: ERROR");
		
	}

	public void runWaypointsTests() throws IOException {
		System.out.println("\n------WAYPOINT TESTS------");
		
		String waypointIdExample = "W00001";
		//UPLOAD
		System.out.println("WAYPOINT TEST: Upload a Waypoint(ID=" + waypointIdExample + ")");
		Waypoint waypointTest = new Waypoint(waypointIdExample,
				"Estación Central",
				 "-5.851008", "43.369122",
				"5");
		if(waypointTest.upload(token.getAccess_token())) System.out.println("Result: OK");
		else System.out.println("Result: ERROR");

		
		//GET
		System.out.println("\nWAYPOINT TEST: Get a Waypoint by ID (ID=" + waypointIdExample + ")");
		Waypoint waypointGet = new Waypoint();
		if(waypointGet.get(token.getAccess_token(), waypointIdExample)) {
			System.out.println("Result: OK");
			System.out.println("\t Obtained Waypoint information");
			System.out.println("\t ID = " + waypointGet.getWa_id());
			System.out.println("\t Name = " +  waypointGet.getWa_name());
			System.out.println("\t Longitude = " +  waypointGet.getWa_longitude());
			System.out.println("\t Latitude = " +  waypointGet.getWa_latitude());
			System.out.println("\t Num Current Lines = " +  waypointGet.getWa_numConcurrentLines());
			
		} else System.out.println("Result: ERROR");

//		//DELETE
//		System.out.println("\nWaypoint TEST: Delete a Waypoint by ID (ID=" + waypointIdExample + ")");
//		if(Request.delete(token.getAccess_token(), waypointIdExample)) {
//			System.out.println("Result: OK");
//			System.out.println("\t Deleted Waypoint ID:" + waypointIdExample);
//		}
//		else
//			 System.out.println("Result: ERROR");
		
	}

	
	
	
	//Load fake data in the Management web application
	public void loadFakeData() throws IOException {
		//Info Taxis
		String[] ids = {"T00001",
				"T00002",
				"T00003",
				"T00004",
				"T00005",
				"T00006",
				"T00007",
				"T00008",
				"T00009",
				"T00010"};
		String[] longs = {"-5.8447600",
				"-5.8496600",
				"-5.8481600",
				"-5.8471600",
				"-5.8462600",
				"-5.8453600",
				"-5.8449600",
				"-5.8330600",
				"-5.8429800",
				"-5.8413800"};
		String[] lats = {"43.3622900",
				"43.3602900",
				"43.3692900",
				"43.3612900",
				"43.3622900",
				"43.3682900",
				"43.3602900",
				"43.3602900",
				"43.3632900",
				"43.3632900"};
		String[] speed = {"10",
				"40",
				"23",
				"12",
				"9",
				"80",
				"53",
				"0",
				"0",
				"35"};
		String[] state = {"AVAILABLE",
				"AVAILABLE",
				"OCCUPIED",
				"OCCUPIED",
				"AVAILABLE",
				"OUTOFSERVICE",
				"AVAILABLE",
				"OCCUPIED",
				"AVAILABLE",
				"OCCUPIED"};
		String[] date = {"01/01/16",
				"01/01/16",
				"01/02/16",
				"01/01/16",
				"01/01/16",
				"01/01/16",
				"01/02/16",
				"01/01/16",
				"01/01/16",
				"01/02/16"};
		for(int i=0; i<10; i++)
		{
			Taxi taxi = new Taxi(ids[i], "12/12/12", longs[i] ,lats[i] ,speed[i],
					state[i]);
			taxi.upload(token.getAccess_token());
		}
		
		
		
		//Info Requests
		String[] idsr = {"R00001",
				"R00002",
				"R00003",
				"R00004",
				"R00005",
				"R00006",
				"R00007",
				"R00008",
				"R00009",
				"R00010"};
		String[] pid = {"P00001",
				"P00002",
				"P00003",
				"P00004",
				"P00005",
				"P00006",
				"P00007",
				"P00008",
				"P00009",
				"P00010"};
		String[] swid = {"W00001",
				"W00002",
				"W00003",
				"W00004",
				"W00005",
				"W00006",
				"W00007",
				"W00008",
				"W00009",
				"W00010",
				"W00011",
				"W00012",
				"W00013",
				"W00014",
				"W00015",
				"W00016",
				"W00017",
				"W00018",
				"W00019",
				"W00020",
				"W00021",
				"W00022",
				"W00023",
				"W00024",
				"W00025",
				"W00026",
				"W00027",
				"W00028",
				"W00029",
				"W00030",
				"W00031",
				"W00032",
				"W00033",
				"W00034",
				"W00035",
				"W00036",
				"W00037",
				"W00038",
				"W00039",
				"W00040"};
		String[] stater = {"PLANNED",
				"FINISHED",
				"PLANNED",
				"FINISHED",
				"ACTIVE",
				"PLANNED",
				"CANCELLED",
				"ACTIVE",
				"PLANNED",
				"ACTIVE"};
		
		String[] stime = {"2016/01/30 11:59:59",
				"2016/01/30 12:29:59",
				"2016/01/30 11:23:59",
				"2016/01/30 09:56:59",
				"2016/01/30 05:12:59",
				"2016/01/30 23:34:59",
				"2016/01/30 23:31:59",
				"2016/01/30 04:01:59",
				"2016/01/30 11:34:59",
				"2016/01/30 11:23:59"};
		//Request_Steps Array creation
		List<Request_Step> requestStepTest = new ArrayList<Request_Step>();
		requestStepTest.add(new Request_Step("W00001" + "S000",
				"T00001",
				"W00001", "2016/01/30 11:59:59", 
				"W00002", "2016/01/30 12:59:59", "20"));
		requestStepTest.add(new Request_Step("W00001" + "S001",
				"T00001",
				"W00002", "2016/01/30 12:19:59", 
				"W00003", "2016/01/30 12:49:59", "30"));
		requestStepTest.add(new Request_Step("W00001" + "S002",
				"T00001",
				"W00003", "2016/01/30 12:49:59", 
				"W00004", "2016/01/30 12:59:59", "10"));
		for(int i=0; i<10; i++)
		{
			Request request = new Request(idsr[i],pid[i],swid[i],stime[i],swid[i+10],"2016/01/30 12:29:59",
					"23", "12", "3", stater[i],requestStepTest);
			request.upload(token.getAccess_token());
		}
		
		
		// Info Waypoints
		for (int i = 0; i< 40; i++){
			//delete
//			Waypoint waypoint = new Waypoint();
//			waypoint.delete(token.getAccess_token(), swid[i]);
			
			int numLine = (int) Math.floor(Math.random()*6+1);
			int longitude = (int) Math.floor(Math.random()*26+32); 
			int latitute = (int) Math.floor(Math.random()*11+58);
			Waypoint waypoint = new Waypoint(swid[i],"ExampleWaypoint_" 
			+ i,"-5.8"+longitude+"1600", "43.3"+ latitute+"2900", numLine+"");
			waypoint.upload(token.getAccess_token());
		}
		
	}
			
	
	
	
	
	
	
}
