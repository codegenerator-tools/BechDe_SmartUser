package com.pratice.utils;

import android.content.Context;
import android.location.Location;


public class LocationUtils {

	private LocationUtils() {
		// private
	}

	private static class SchSingletonHolder {
		public static final LocationUtils INSTANCE = new LocationUtils();
	}

	public static LocationUtils getInstance() {
		return SchSingletonHolder.INSTANCE;
	}
	
	public Location getCurrentLocation() {
		
		Location location = null;
		try {
			location = new Location(Context.LOCATION_SERVICE);
			
			location.setLatitude(77.333);
			location.setLongitude(10.444);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return location;
	}

	/**
	 *fetch the location
	 */
	public void startNavigationEngine() {
		
	}

	public CharSequence getLocation() {
		// TODO Auto-generated method stub
		return "Olx, Sector - 31";
	}
}
