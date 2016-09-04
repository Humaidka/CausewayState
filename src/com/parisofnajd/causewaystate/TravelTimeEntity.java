package com.parisofnajd.causewaystate;

import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import org.joda.time.DateTime;
import org.joda.time.Duration;


public class TravelTimeEntity {

	public static void persistKSA2BHR(String start, String end, Date date,Date tt) {
	
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
		Entity at = new Entity("TravelTime");
		at.setProperty("From", start);
		at.setProperty("To", end);
		at.setProperty("DateTime", date);
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
}
 