package entities;

import java.io.IOException;

import Utils.Constants;
import Utils.Sender;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Entity.StatusCode;
import entities.Entity.ContextElement.Attribute;

public class Waypoint {
	
	String wa_id;
	String wa_name;
	String wa_longitude;
	String wa_latitude;
	String wa_numConcurrentLines;
	
	public Waypoint(String wa_id, String wa_name, String wa_longitude, 	String wa_latitude,
			String wa_numConcurrentLines){
		setWa_id(wa_id);
		setWa_latitude(wa_latitude);
		setWa_longitude(wa_longitude);
		setWa_name(wa_name);
		setWa_numConcurrentLines(wa_numConcurrentLines);	
	}
	
	public Waypoint() {
		// TODO Auto-generated constructor stub
	}


	//CREATE A NEW WAYPOINT IN THE CLOUD
	public boolean upload(String authToken) throws IOException{
		String response = Sender.send(authToken, Constants.UPLOAD_API_URL,"POST", this.toJson());
		//Analyze response code to check problems
		ObjectMapper mapper = new ObjectMapper();
		UpdateResponse updateResponse = mapper.readValue(response, UpdateResponse.class);
		if (updateResponse.contextResponses[0].statusCode.code.equals("200")) return true;
		return false;	
	}
	

	//GET A WAYPOINT GIVEN AN ID
	public boolean get(String authToken, String id) 
			throws JsonParseException, JsonMappingException, IOException{
		
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + id,"GET");

		//JSON from String to Token
		try {
			ObjectMapper mapper = new ObjectMapper();
			Entity entity = mapper.readValue(response, Entity.class);
		
			this.setWa_id(entity.contextElement.id);
			Attribute[] attributes = entity.contextElement.attributes;
			for(int i=0; i<attributes.length; i++){
				if(attributes[i].name.equals("wa_numConcurrentLines")) this.setWa_numConcurrentLines(attributes[i].value);
				else if(attributes[i].name.equals("wa_name")) this.setWa_name(attributes[i].value);	
				else if(attributes[i].name.equals("position")) {
					this.setWa_latitude(attributes[i].value.split(",")[0]);
					this.setWa_longitude(attributes[i].value.split(",")[1]);
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
		
	}


	//DELETE A WAYPOINT IN THE CLOUD
	//2 ways
	//1) Method .delete(token) used in an existing waypoint object 
	//		Useful in those cases where the system is working with the whole object
	//2) Static method .delete(token, ID) with no object defined
	//		Useful in cases where the system only knows the ID. No "Object" defined is required
	public boolean delete(String authToken) throws IOException{
		return delete(authToken, this.getWa_id());
	}
	public static boolean delete(String authToken, String waypoint_id) throws IOException{
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + waypoint_id,"DELETE");
		//Analyze response code to check problems
		ObjectMapper mapper = new ObjectMapper();
		StatusCode statusCode = mapper.readValue(response, StatusCode.class);
		if (statusCode.code.equals("200")) return true;
		return false;	
	}
	







	public String toJson(){
		return ("{\"contextElements\": [" + 
			        "{\"type\": \"Waypoint\","+
			            "\"isPattern\": \"false\","+
			            "\"id\": \"" + getWa_id() + "\","+
			            "\"attributes\": ["+

			                    "{\"name\": \"wa_name\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getWa_name() + "\"}," +

								"{ \"name\": \"position\"," +
								"\"type\": \"coords\"," +
								"\"value\": \"" + getWa_latitude() +","+ getWa_longitude() +"\"," +
								"\"metadatas\": [{" + 
								        "\"name\": \"location\"," +
								        "\"type\": \"string\"," + 
								        "\"value\": \"WGS84\"}]}," +

			                    "{\"name\": \"wa_numConcurrentLines\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getWa_numConcurrentLines() + "\"}" +

			                    "]}]," +
			            "\"updateAction\": \"APPEND\"}");
		}
	
	
	
	//GETTERS / SETTERS
	public String getWa_id() {
		return wa_id;
	}
	public void setWa_id(String wa_id) {
		this.wa_id = wa_id;
	}
	public String getWa_name() {
		return wa_name;
	}
	public void setWa_name(String wa_name) {
		this.wa_name = wa_name;
	}
	public String getWa_longitude() {
		return wa_longitude;
	}
	public void setWa_longitude(String wa_longitude) {
		this.wa_longitude = wa_longitude;
	}
	public String getWa_latitude() {
		return wa_latitude;
	}
	public void setWa_latitude(String wa_latitude) {
		this.wa_latitude = wa_latitude;
	}
	public String getWa_numConcurrentLines() {
		return wa_numConcurrentLines;
	}
	public void setWa_numConcurrentLines(String wa_numConcurrentLines) {
		this.wa_numConcurrentLines = wa_numConcurrentLines;
	}
	

	
	
	
}
