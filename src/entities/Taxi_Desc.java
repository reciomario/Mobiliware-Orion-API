package entities;

import java.io.IOException;

import Utils.Constants;
import Utils.Sender;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Entity.StatusCode;
import entities.Entity.ContextElement.Attribute;



public class Taxi_Desc{
	
	String td_id;
	String td_plate;
	String td_license;
	String td_size;
	
	
	public Taxi_Desc(String tp_id, String td_plate,
			String td_license, String td_size){
		
		setTd_id(tp_id);
		setTd_plate(td_plate);
		setTd_license(td_license);
		setTd_size(td_size);

	}
	
	public Taxi_Desc() {
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
		
			this.setTd_id(entity.contextElement.id);
			Attribute[] attributes = entity.contextElement.attributes;
			for(int i=0; i<attributes.length; i++){
				if(attributes[i].name.equals("td_plate")) this.setTd_plate(attributes[i].value);
				else if(attributes[i].name.equals("td_license")) this.setTd_license(attributes[i].value);	
				else if(attributes[i].name.equals("td_size")) this.setTd_size(attributes[i].value);		
			}

			return true;
		} catch (Exception e) {
			return false;
		}
		
	}


	//DELETE AN ENTITY IN THE CLOUD
	//2 ways
	//1) Method .delete(token) used in an existing entity object 
	//		Useful in those cases where the system is working with the whole object
	//2) Static method .delete(token, ID) with no object defined
	//		Useful in cases where the system only knows the ID. No "Object" defined is required
	public boolean delete(String authToken) throws IOException{
		return delete(authToken, this.getTd_id());
	}
	public static boolean delete(String authToken, String taxiDesc_id) throws IOException{
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + taxiDesc_id,"DELETE");
		//Analyze response code to check problems
		ObjectMapper mapper = new ObjectMapper();
		StatusCode statusCode = mapper.readValue(response, StatusCode.class);
		if (statusCode.code.equals("200")) return true;
		return false;	
	}
	
	
	
	
	
	public String toJson(){
		return ("{\"contextElements\": [" + 
			        "{\"type\": \"Taxi_Desc\","+
			            "\"isPattern\": \"false\","+
			            "\"id\": \"" + getTd_id() + "\","+
			            "\"attributes\": ["+
                   
			                    "{\"name\": \"td_plate\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getTd_plate() + "\"}," +
			                    
								"{\"name\": \"td_size\","+
								"\"type\": \"text\","+
								"\"value\": \"" + getTd_size() + "\"}," +
			                    
			                    "{\"name\": \"td_license\","+
			                    "\"type\": \"text\","+
			                    "\"value\": \"" + getTd_license() + "\"}"+
	
			                    "]}]," +
			            "\"updateAction\": \"APPEND\"}");
		}

	
	//GETTERS SETTERS
	public String getTd_id() {
		return td_id;
	}


	public void setTd_id(String td_id) {
		this.td_id = td_id;
	}


	public String getTd_plate() {
		return td_plate;
	}


	public void setTd_plate(String td_plate) {
		this.td_plate = td_plate;
	}

	public String getTd_license() {
		return td_license;
	}


	public void setTd_license(String td_license) {
		this.td_license = td_license;
	}


	public String getTd_size() {
		return td_size;
	}


	public void setTd_size(String td_size) {
		this.td_size = td_size;
	}
	
	
	

}
