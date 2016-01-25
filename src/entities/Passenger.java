package entities;
import java.io.IOException;
import java.sql.Timestamp;

import Utils.Constants;
import Utils.Sender;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Entity.ContextElement.Attribute;


public class Passenger {
	
	String pa_id;
	String pa_max_price;

	public Passenger(String pa_id, String pa_max_price) {
		
		setPa_id(pa_id);
		setPa_max_price(pa_max_price);

	}

	public Passenger(){
		
	}
	
	
	//GET A PASSSENGER GIVEN AN ID
	public boolean get(String authToken, String id) 
			throws JsonParseException, JsonMappingException, IOException{
		
		String response = Sender.send(authToken, Constants.CONTEXT_API_URL + id,"GET");

		//JSON from String to Token
		try {
			ObjectMapper mapper = new ObjectMapper();
			Entity entity = mapper.readValue(response, Entity.class);
		
			this.setPa_id(entity.contextElement.id);
			Attribute[] attributes = entity.contextElement.attributes;
			for(int i=0; i<attributes.length; i++){
				if(attributes[i].name.equals("pa_max_price")) this.setPa_max_price(attributes[i].value);		
			}

			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	
	//*GETTERS & SETTERS
	public String getPa_id() {
		return pa_id;
	}

	public void setPa_id(String pa_id) {
		this.pa_id = pa_id;
	}

	public String getPa_max_price() {
		return pa_max_price;
	}

	public void setPa_max_price(String pa_max_price) {
		this.pa_max_price = pa_max_price;
	}

	
	public String toJson(){
	return ("{\"contextElements\": [" + 
		        "{\"type\": \"Passenger\","+
		            "\"isPattern\": \"false\","+
		            "\"id\": \"P" + getPa_id() + "\","+
		            "\"attributes\": ["+
		                "{\"name\": \"pa_max_price\","+
		                    "\"type\": \"float\","+
		                    "\"value\": \"" + getPa_max_price() + "\"}]}]," +
		            "\"updateAction\": \"APPEND\"}");
		
	}

	
}
