package entities;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Entity.ContextElement.Attribute;
import entities.Entity.StatusCode;
import Utils.Constants;
import Utils.Sender;


public class Taxi {
	

	String ta_id;
	String ta_date;
	String ta_longitude;
	String ta_latitude;
	String ta_speed;
	String ta_state;
	
	public Taxi(String ta_id, String ta_date, String ta_longitude, String ta_latitude,
			String ta_speed, String ta_state) { 
		
		setTa_id(ta_id);
		setTa_date(ta_date);
		setTa_latitude(ta_latitude);
		setTa_longitude(ta_longitude);
		setTa_speed(ta_speed);
		setTa_state(ta_state);
	}


	public Taxi() {
		// TODO Auto-generated constructor stub
	}


	//CREATE A NEW TAXI IN THE CLOUD
	public boolean upload(String authToken) throws IOException{
		String response = Sender.send(authToken, Constants.UPLOAD_API_URL,"POST", this.toJson());
		//Analyze response code to check problems
		ObjectMapper mapper = new ObjectMapper();
		UpdateResponse updateResponse = mapper.readValue(response, UpdateResponse.class);
		if (updateResponse.contextResponses[0].statusCode.code.equals("200")) return true;
		return false;	
	}
	

	//GET A TAXI GIVEN AN ID
	public boolean get(String authToken, String id) 
			throws JsonParseException, JsonMappingException, IOException{
		
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + id,"GET");

		//JSON from String to Token
		try {
			ObjectMapper mapper = new ObjectMapper();
			Entity entity = mapper.readValue(response, Entity.class);
		
			this.setTa_id(entity.contextElement.id);
			Attribute[] attributes = entity.contextElement.attributes;
			for(int i=0; i<attributes.length; i++){
				if(attributes[i].name.equals("ta_date")) this.setTa_date(attributes[i].value);
				else if(attributes[i].name.equals("ta_speed")) this.setTa_speed(attributes[i].value);
				else if(attributes[i].name.equals("ta_state")) this.setTa_state(attributes[i].value);	
				else if(attributes[i].name.equals("position")) {
					this.setTa_latitude(attributes[i].value.split(",")[0]);
					this.setTa_longitude(attributes[i].value.split(",")[1]);
				}
				
			}

			return true;
		} catch (Exception e) {
			return false;
		}
		
	}


	//DELETE A TAXI IN THE CLOUD
	//2 ways
	//1) Method .delete(token) used in an existing taxi object 
	//		Useful in those cases where the system is working with the whole object
	//2) Static method .delete(token, ID) with no object defined
	//		Useful in cases where the system only knows the ID. No "Object" defined is required
	public boolean deleteTaxi(String authToken) throws IOException{
		return deleteTaxi(authToken, this.getTa_id());
	}
	public static boolean deleteTaxi(String authToken, String taxi_id) throws IOException{
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + taxi_id,"DELETE");
		//Analyze response code to check problems
		ObjectMapper mapper = new ObjectMapper();
		StatusCode statusCode = mapper.readValue(response, StatusCode.class);
		if (statusCode.code.equals("200")) return true;
		return false;	
	}
	
	public String toJson(){
		return ("{\"contextElements\": [" + 
			        "{\"type\": \"Taxi\","+
			            "\"isPattern\": \"false\","+
			            "\"id\": \"" + getTa_id() + "\","+
			            "\"attributes\": ["+

			                    "{\"name\": \"ta_date\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getTa_date() + "\"}," +

								"{ \"name\": \"position\"," +
								"\"type\": \"coords\"," +
								"\"value\": \"" + getTa_latitude() +","+ getTa_longitude() +"\"," +
								"\"metadatas\": [{" + 
								        "\"name\": \"location\"," +
								        "\"type\": \"string\"," + 
								        "\"value\": \"WGS84\"}]}," +

			                    "{\"name\": \"ta_speed\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getTa_speed() + "\"}," +

			                    "{\"name\": \"ta_state\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getTa_state() + "\"}" +

			                    "]}]," +
			            "\"updateAction\": \"APPEND\"}");
		}


	
	
	//GETTERS & SETTERS
	public String getTa_id() {
		return ta_id;
	}


	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}


	public String getTa_date() {
		return ta_date;
	}


	public void setTa_date(String ta_date) {
		this.ta_date = ta_date;
	}


	public String getTa_longitude() {
		return ta_longitude;
	}


	public void setTa_longitude(String ta_longitude) {
		this.ta_longitude = ta_longitude;
	}


	public String getTa_latitude() {
		return ta_latitude;
	}


	public void setTa_latitude(String ta_latitude) {
		this.ta_latitude = ta_latitude;
	}


	public String getTa_speed() {
		return ta_speed;
	}


	public void setTa_speed(String ta_speed) {
		this.ta_speed = ta_speed;
	}


	public String getTa_state() {
		return ta_state;
	}


	public void setTa_state(String ta_state) {
		this.ta_state = ta_state;
	}
	


}
