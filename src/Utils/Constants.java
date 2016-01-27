package Utils;

public interface Constants {

	 public static boolean DEBUG = false; //true = DEBUG MODE ON
	
	 //FIWARE AUTH SERVER
	 public static final String AUTH_URL= "https://cosmos.lab.fiware.org:13000/cosmos-auth/v1/token";
	 
	 //CREDENTIALS
	 public static final String OAUTH_USER_NAME = "email";
	 public static final String OAUTH_USER_PASS = "pass";
	 
	 //MOBILIWARE API URLs
	 public static final String MOBILIWARE_API_IP = "http://130.206.112.180";
	 public static final String MOBILIWARE_API_PORT = "80";
	 
	 //OPERATIONS URLS
	 public static final String CONTEXT_API_URL = MOBILIWARE_API_IP + ":" + MOBILIWARE_API_PORT
			 + "/v1/contextEntities/";
	 public static final String UPLOAD_API_URL = MOBILIWARE_API_IP + ":" + MOBILIWARE_API_PORT
			 + "/v1/updateContext/";
	 
	 
	 //TAXI STATE
	 public static final String TAXI_STATE_AVAILABLE = "AVAILABLE";
	 public static final String TAXI_STATE_OCCUPIED = "OCCUPIED";
	 public static final String TAXI_STATE_OUTOFSERVICE = "OUTOFSERVICE";
	 
	 
	 ////		IDS MANAGEMENT
	 //---------------------------------
	 //Taxi/Taxi_Desc 		->
	 //			Taxi - 			TXXXXX
	 //			Taxi_Desc - 	TXXXXXD
	 //Request/RequestStep 	->
	 //			Request - 		RXXXXX
	 //			Request_Step- 	RXXXXXSXXX
	 //Passenger 			->	PXXXXX
	 //Waypoint 			->	WXXXXX	
	 //	
	 // (UNDER DEVELOPMENT)
	 //
	 //
	 //
	 ////		STATES MANAGEMENT
	 //--------------------------------
	 //Request				->	re_state = PLANNED, ACTIVE, FINISHED
	 //			PLANNED 	- Requests planned for a future date 
	 //			ACTIVE		- Requests active right now
	 //			FINISHED	- Requests completed
	 //			CANCELLED	- Requests cancelled before been finished
	 //Taxi					-> ta_state
	 //			AVAILABLE	- Taxi ready for accepting requests
	 //			OCCUPIED	- Taxi busy with requests (not available)
	 //			OUTOFSERVICE- Taxi not available
	 //
	 //
	 //
	 //
	 //
	 ////		UNITS MANAGEMENT
	 //--------------------------------
	 //Dates			-		?
	 //Costs 			-		€?
	 //Times			-		mins?
	 //
	 //
}		
