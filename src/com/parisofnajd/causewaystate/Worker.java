package com.parisofnajd.causewaystate;

import java.io.*;
import javax.servlet.http.*;
import java.util.logging.Logger;

import com.google.maps.DirectionsApi; 
import com.google.maps.GeoApiContext;
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.AddressType;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodedWaypointStatus;
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
	private GeoApiContext context = new GeoApiContext();
	private static final Logger log = Logger.getLogger(Worker.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		DirectionsResult result = null;
		try {
			result = testKSA2BHR();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.getWriter().println(result.toString());
	}
	
	private DirectionsResult testKSA2BHR() throws Exception {
		context.setApiKey("AIzaSyAo0AI9Ym9NUweWLrq9uluGMpHmsyvLUrU");
		DirectionsResult result = DirectionsApi.newRequest(context)
	        .origin("Khobar, Saudi Arabia")
	        .destination("Bahrain").await();

	    assertNotNull(result.routes);
	    return result;
	}
	
}