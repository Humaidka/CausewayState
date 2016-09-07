package com.parisofnajd.causewaystate;

import java.io.*;
import javax.servlet.http.*;
import java.util.logging.Logger;

import com.google.maps.DirectionsApi; 
import com.google.maps.GeoApiContext;
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.GaeRequestHandler;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.AddressType;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodedWaypointStatus;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("serial")
public class Worker extends HttpServlet {
	private GeoApiContext context = new GeoApiContext(new GaeRequestHandler());
	private static final Logger log = Logger.getLogger(Worker.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		DirectionsResult result = null;
		context.setApiKey("AIzaSyAo0AI9Ym9NUweWLrq9uluGMpHmsyvLUrU");
		
		try {
			KSA2BHR();
			BHR2KSA();
			
		} catch (Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void KSA2BHR() throws Exception {
		DateTime date = new DateTime("Asia/Riyadh");
		LatLng origin = new LatLng(26.218625, 50.206226);
		LatLng dest = new LatLng(26.172190,50.458094);
		DirectionsResult result = DirectionsApi.newRequest(context)
	        .origin(origin)
	        .destination(dest).await();
		 TravelTimeEntity.persist("BHR", "KSA", date, result.routes[0].legs[0].duration.humanReadable,result.routes[0].legs[0].duration.inSeconds);			    
	}
	
	private void BHR2KSA() throws Exception {
		DateTime date = new DateTime("Asia/Riyadh");
		LatLng origin = new LatLng(26.172396,50.459185);
		LatLng dest = new LatLng(26.219009,50.208105);
		DirectionsResult result = DirectionsApi.newRequest(context)
	        .origin(origin)
	        .destination(dest).await();
	    TravelTimeEntity.persist("BHR", "KSA", date, result.routes[0].legs[0].duration.humanReadable,result.routes[0].legs[0].duration.inSeconds);
	}
	
}