package Utils;

public interface Constants {

	 public static boolean DEBUG = false; //true = DEBUG MODE ON
	
	 //FIWARE AUTH SERVER
	 public static final String AUTH_URL= "https://cosmos.lab.fiware.org:13000/cosmos-auth/v1/token";
	 
	 //CREDENTIALS
	 public static final String OAUTH_USER_NAME = "mario.recio@treelogic.com";
	 public static final String OAUTH_USER_PASS = "tr33logic";
	 
	 //MOBILIWARE API URLs
	 public static final String MOBILIWARE_API_IP = "http://130.206.112.180";
	 public static final String MOBILIWARE_API_PORT = "80";
	 
	 //OPERATIONS URLS
	 public static final String CONTEXT_API_URL = MOBILIWARE_API_IP + ":" + MOBILIWARE_API_PORT
			 + "/v1/contextEntities/";
	 public static final String UPLOAD_API_URL = MOBILIWARE_API_IP + ":" + MOBILIWARE_API_PORT
			 + "/v1/updateContext/";
	 
	 
	 //MOBILIWARE API URLs
	 public static final String TAXI_STATE_AVAILABLE = "AVAILABLE";
	 public static final String TAXI_STATE_OCCUPIED = "OCCUPIED";
	 public static final String TAXI_STATE_OUTOFSERVICE = "OUTOFSERVICE1";
}
