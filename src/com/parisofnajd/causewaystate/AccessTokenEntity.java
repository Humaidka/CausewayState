package com.parisofnajd.causewaystate;

import java.util.Date;
import 

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;



public class AccessTokenEntity {

	public static void persist(Date date,Date time,Date tt) {
	
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
		Entity at = new Entity("TravelTime");
		at.setProperty("From", "KSA");
		at.setProperty("To", "BHR");
		at.setProperty("Date", date);
		at.setProperty("Time", time);
		at.setProperty("TT", tt);
		ds.put(at);

	}
	
	public static String fetchAccessToken(long userid) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey("AccessTokens", userid);
		Entity accesstoken=null;
		try {
			accesstoken=ds.get(k);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return accesstoken.getProperty("Token").toString();
	}
	
public static String fetchAccessTokenSecret(long userid) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey("TravelTime");
		Entity accesstoken=null;
		try {
			accesstoken=ds.get(k);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return accesstoken.getProperty("Secret").toString();
	}
}
 