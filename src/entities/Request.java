package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.Constants;
import Utils.Sender;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Entity.StatusCode;
import entities.Entity.ContextElement.Attribute;



public class Request {

	String re_id;		//RXXXXX
	String pa_id;
	String re_startWaypointId;
	String re_startTime;
	String re_endWaypointId;
	String re_endTime;
	String re_totalTime;
	String re_totalCost;
	String re_numSteps;
	String re_state;	//PLANNED, ACTIVE, FINISHED, CANCELLED
	
	List<Request_Step> re_requestSteps = new ArrayList<Request_Step>();

	
	public Request(String re_id, String pa_id, String re_startWaypointId, String re_startTime, 
			String re_endWaypoint, String re_endTime,
			String re_totalTime, String re_totalCost, 
			String re_numSteps, String re_state, List<Request_Step> re_requestSteps){
		
		setRe_id(re_id);
		setRe_startTime(re_startTime);
		setRe_startWaypointId(re_startWaypointId);
		setRe_endTime(re_endTime);
		setRe_endWaypointId(re_endWaypoint);
		setRe_numSteps(re_numSteps);
		setRe_state(re_state);
		setRe_totalCost(re_totalCost);
		setRe_totalTime(re_totalTime);
		setPa_id(pa_id);
		
		setRe_requestSteps(re_requestSteps);
	}
	
	public Request() {
		// TODO Auto-generated constructor stub
	}
	
	//CREATE A NEW REQUEST IN THE CLOUD
	public boolean upload(String authToken) throws IOException{
		//Upload Steps
		for (Request_Step requesStep : re_requestSteps) {
			if(!requesStep.upload(authToken)) return false;
		}
		//Upload Request
		String response = Sender.send(authToken, Constants.UPLOAD_API_URL,"POST", this.toJson());
		//Analyze response code to check problems
		ObjectMapper mapper = new ObjectMapper();
		UpdateResponse updateResponse = mapper.readValue(response, UpdateResponse.class);
		if (updateResponse.contextResponses[0].statusCode.code.equals("200")) return true;
		return false;	
	}
	
	//GET A REQUEST GIVEN AN ID
	public boolean get(String authToken, String id) 
			throws JsonParseException, JsonMappingException, IOException{
		
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + id,"GET");

		//JSON from String to Object
		try {
			ObjectMapper mapper = new ObjectMapper();
			Entity entity = mapper.readValue(response, Entity.class);
		
			this.setRe_id(entity.contextElement.id);
			Attribute[] attributes = entity.contextElement.attributes;
			//Get Request info
			for(int i=0; i<attributes.length; i++){
				if(attributes[i].name.equals("re_startWaypointId")) this.setRe_startWaypointId(attributes[i].value);
				else if(attributes[i].name.equals("re_startTime")) this.setRe_startTime(attributes[i].value);
				else if(attributes[i].name.equals("re_endWaypointId")) this.setRe_endWaypointId(attributes[i].value);
				else if(attributes[i].name.equals("re_endTime")) this.setRe_endTime(attributes[i].value);
				else if(attributes[i].name.equals("re_totalTime")) this.setRe_totalTime(attributes[i].value);	
				else if(attributes[i].name.equals("re_totalCost")) this.setRe_totalCost(attributes[i].value);	
				else if(attributes[i].name.equals("re_numSteps")) this.setRe_numSteps(attributes[i].value);	
				else if(attributes[i].name.equals("re_state")) this.setRe_state(attributes[i].value);	
				else if(attributes[i].name.equals("pa_id")) this.setPa_id(attributes[i].value);	
			}
			
			//Get Request_Step s Info
			for(int i=0; i<new Integer(this.getRe_numSteps()); i++){
				Request_Step requestStepGet = new Request_Step();
				requestStepGet.get(authToken, id + "S" + String.format("%03d", i));
				re_requestSteps.add(requestStepGet);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	//DELETE A REQUEST IN THE CLOUD
	//2 ways
	//1) Method .delete(token) used in an existing taxi object 
	//		Useful in those cases where the system is working with the whole object
	//2) Static method .delete(token, ID) with no object defined
	//		Useful in cases where the system only knows the ID. No "Object" defined is required
	public boolean delete(String authToken) throws IOException{
		return delete(authToken, this.getRe_id());
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
			        "{\"type\": \"Request\","+
			            "\"isPattern\": \"false\","+
			            "\"id\": \"" + getRe_id() + "\","+
			            "\"attributes\": ["+
			            		"{\"name\": \"re_startWaypointId\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getRe_startWaypointId() + "\"}," +
			                    
								"{\"name\": \"pa_id\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getPa_id() + "\"}," +
			                    
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
								
								"{\"name\": \"re_numSteps\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getRe_numSteps() + "\"}," +
								
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
	
	public String getRe_numSteps() {
		return re_numSteps;
	}


	public void setRe_numSteps(String re_numSteps) {
		this.re_numSteps = re_numSteps;
	}

	public List<Request_Step> getRe_requestSteps() {
		return re_requestSteps;
	}

	public void setRe_requestSteps(List<Request_Step> re_requestSteps) {
		this.re_requestSteps = re_requestSteps;
	}

	public String getPa_id() {
		return pa_id;
	}

	public void setPa_id(String pa_id) {
		this.pa_id = pa_id;
	}

	
	
	
	
}
