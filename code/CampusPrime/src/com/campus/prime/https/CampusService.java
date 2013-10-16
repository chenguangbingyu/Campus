package com.campus.prime.https;

public class CampusService {
	
	private CampusClient client;
	
	//private String token = "eb3d139ec756bc8dab04d4d391b60dbbafacdeec";
	
	public CampusService() {
		// TODO Auto-generated constructor stub
		this(new CampusClient());
	}
	public CampusService(CampusClient client) {
		// TODO Auto-generated constructor stub
		this.client = client;
	}

	
	public CampusClient getClient(){
		//this.client = client.setCredential(token);
		return this.client;
	}
	
	
	public CampusRequest createRequest(){
		return new CampusRequest();
	}
	
	
}
