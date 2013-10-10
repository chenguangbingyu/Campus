package com.campus.prime.https;

public class CampusService {
	
	protected final CampusClient client;
	
	public CampusService() {
		// TODO Auto-generated constructor stub
		this(new CampusClient());
	}
	public CampusService(CampusClient client) {
		// TODO Auto-generated constructor stub
		this.client = client;
	}
	
	public CampusClient getClient(){
		return this.client;
	}
	
	
	public CampusRequest createRequest(){
		return new CampusRequest();
	}
	
}
