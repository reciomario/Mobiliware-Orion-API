package entities;

import java.io.IOException;

import Utils.Constants;
import Utils.Sender;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Entity.StatusCode;
import entities.Entity.ContextElement.Attribute;

public class Request_Step {

	String rs_id;   	//RXXXXXSXXX
	String ta_id;
	String rs_startWaypointId;
	String rs_startTime;
	String rs_endWaypointId;
	String rs_endTime;
	String rs_totalTime;
	
	public Request_Step(String rs_id, String ta_id, String rs_startWaypointId, String rs_startTime, 
			String rs_endWaypoint, String rs_endTime,
			String rs_totalTime){
		
		setRs_endTime(rs_endTime);
		setTa_id(ta_id);
		setRs_endWaypointId(rs_endWaypoint);
		setRs_id(rs_id);
		setRs_startTime(rs_startTime);
		setRs_startWaypointId(rs_startWaypointId);
		setRs_totalTime(rs_totalTime);
	}
	
	public Request_Step() {
		// TODO Auto-generated constructor stub
	}
	
	//CREATE A NEW REQUEST_STEP IN THE CLOUD
	public boolean upload(String authToken) throws IOException{
		String response = Sender.send(authToken, Constants.UPLOAD_API_URL,"POST", this.toJson());
		//Analyze response code to check problems
		ObjectMapper mapper = new ObjectMapper();
		UpdateResponse updateResponse = mapper.readValue(response, UpdateResponse.class);
		if (updateResponse.contextResponses[0].statusCode.code.equals("200")) return true;
		return false;	
	}
	
	//GET A REQUEST_STEP GIVEN AN ID
	public boolean get(String authToken, String id) 
			throws JsonParseException, JsonMappingException, IOException{
		
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + id,"GET");

		//JSON from String to Object
		try {
			ObjectMapper mapper = new ObjectMapper();
			Entity entity = mapper.readValue(response, Entity.class);
		
			this.setRs_id(entity.contextElement.id);
			Attribute[] attributes = entity.contextElement.attributes;
			for(int i=0; i<attributes.length; i++){
				if(attributes[i].name.equals("rs_startWaypointId")) this.setRs_startWaypointId(attributes[i].value);
				else if(attributes[i].name.equals("rs_startTime")) this.setRs_startTime(attributes[i].value);
				else if(attributes[i].name.equals("rs_endWaypointId")) this.setRs_endWaypointId(attributes[i].value);
				else if(attributes[i].name.equals("rs_endTime")) this.setRs_endTime(attributes[i].value);
				else if(attributes[i].name.equals("rs_totalTime")) this.setRs_totalTime(attributes[i].value);	
				else if(attributes[i].name.equals("ta_id")) this.setTa_id(attributes[i].value);	
			}

			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//DELETE A REQUEST STEP IN THE CLOUD
	//2 ways
	//1) Method .delete(token) used in an existing taxi object 
	//		Useful in those cases where the system is working with the whole object
	//2) Static method .delete(token, ID) with no object defined
	//		Useful in cases where the system only knows the ID. No "Object" defined is required
	public boolean delete(String authToken) throws IOException{
		return delete(authToken, this.getRs_id());
	}
	public static boolean delete(String authToken, String request_id) throws IOException{
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + request_id,"DELETE");
		//Analyze response code to check problems
		ObjectMapper mapper = new ObjectMapper();
		StatusCode statusCode = mapper.readValue(response, StatusCode.class);
		if (statusCode.code.equals("200")) return true;
		return false;	
	}

	
	
	
	
	public String toJson(){
		return ("{\"contextElements\": [" + 
			        "{\"type\": \"Request_Step\","+
			            "\"isPattern\": \"false\","+
			            "\"id\": \"" + getRs_id() + "\","+
			            "\"attributes\": ["+
			            
								"{\"name\": \"ta_id\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getTa_id() + "\"}," +

			            		"{\"name\": \"rs_startWaypointId\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getRs_startWaypointId() + "\"}," +
			                    
								"{\"name\": \"rs_startTime\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRs_startTime() + "\"}," +
								
								"{\"name\": \"rs_endWaypointId\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRs_endWaypointId() + "\"}," +
								
								"{\"name\": \"rs_endTime\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRs_endTime() + "\"}," +
								
								"{\"name\": \"rs_totalTime\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRs_totalTime() + "\"}" +
								
			                    "]}]," +
			            "\"updateAction\": \"APPEND\"}");
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//GETTERS AND SETTERS
	public String getRs_id() {
		return rs_id;
	}
	public void setRs_id(String rs_id) {
		this.rs_id = rs_id;
	}
	public String getRs_startWaypointId() {
		return rs_startWaypointId;
	}
	public void setRs_startWaypointId(String rs_startWaypointId) {
		this.rs_startWaypointId = rs_startWaypointId;
	}
	public String getRs_startTime() {
		return rs_startTime;
	}
	public void setRs_startTime(String rs_startTime) {
		this.rs_startTime = rs_startTime;
	}
	public String getRs_endWaypointId() {
		return rs_endWaypointId;
	}
	public void setRs_endWaypointId(String rs_endWaypointId) {
		this.rs_endWaypointId = rs_endWaypointId;
	}
	public String getRs_endTime() {
		return rs_endTime;
	}
	public void setRs_endTime(String rs_endTime) {
		this.rs_endTime = rs_endTime;
	}
	public String getRs_totalTime() {
		return rs_totalTime;
	}
	public void setRs_totalTime(String rs_totalTime) {
		this.rs_totalTime = rs_totalTime;
	}

	public String getTa_id() {
		return ta_id;
	}

	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}

}
