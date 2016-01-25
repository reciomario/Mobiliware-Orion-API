package entities;



public class Request {

	String re_id;
	String re_startWaypointId;
	String re_startTime;
	String re_endWaypointId;
	String re_endTime;
	String re_totalTime;
	String re_totalCost;
	String re_state;	//PLANNED, ACTIVE, FINISHED
	
	
	public String toJson(){
		return ("{\"contextElements\": [" + 
			        "{\"type\": \"Request\","+
			            "\"isPattern\": \"false\","+
			            "\"id\": \"" + getRe_id() + "\","+
			            "\"attributes\": ["+
			            		"{\"name\": \"re_startWaypointId\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getRe_startWaypointId() + "\"}," +
			                    
								"{\"name\": \"re_startTime\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRe_startTime() + "\"}," +
								
								"{\"name\": \"re_endWaypointId\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRe_endWaypointId() + "\"}," +
								
								"{\"name\": \"re_endTime\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRe_endTime() + "\"}," +
								
								"{\"name\": \"re_totalTime\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRe_totalTime() + "\"}," +
								
								"{\"name\": \"re_totalCost\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRe_totalCost() + "\"}," +
								
								"{\"name\": \"re_state\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRe_state() + "\"}" +

			                    "]}]," +
			            "\"updateAction\": \"APPEND\"}");
		}


	
	//GETTERS SETTERS
	public String getRe_id() {
		return re_id;
	}


	public void setRe_id(String re_id) {
		this.re_id = re_id;
	}


	public String getRe_startWaypointId() {
		return re_startWaypointId;
	}


	public void setRe_startWaypointId(String re_startWaypointId) {
		this.re_startWaypointId = re_startWaypointId;
	}


	public String getRe_startTime() {
		return re_startTime;
	}


	public void setRe_startTime(String re_startTime) {
		this.re_startTime = re_startTime;
	}


	public String getRe_endWaypointId() {
		return re_endWaypointId;
	}


	public void setRe_endWaypointId(String re_endWaypointId) {
		this.re_endWaypointId = re_endWaypointId;
	}


	public String getRe_endTime() {
		return re_endTime;
	}


	public void setRe_endTime(String re_endTime) {
		this.re_endTime = re_endTime;
	}


	public String getRe_totalTime() {
		return re_totalTime;
	}


	public void setRe_totalTime(String re_totalTime) {
		this.re_totalTime = re_totalTime;
	}


	public String getRe_totalCost() {
		return re_totalCost;
	}


	public void setRe_totalCost(String re_totalCost) {
		this.re_totalCost = re_totalCost;
	}


	public String getRe_state() {
		return re_state;
	}


	public void setRe_state(String re_state) {
		this.re_state = re_state;
	}
	
	
	
	
}
