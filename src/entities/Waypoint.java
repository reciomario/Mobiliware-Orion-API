package entities;

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
