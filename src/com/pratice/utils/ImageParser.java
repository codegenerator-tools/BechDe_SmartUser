package com.pratice.utils;

import com.pratice.fragments.ImageCapture;


public class ImageParser {

	private ImageParser() {
		// private
	}

	private static class SchSingletonHolder {
		public static final ImageParser INSTANCE = new ImageParser();
	}

	public static ImageParser getInstance() {
		return SchSingletonHolder.INSTANCE;
	}
	
	public String getTitle() {
		return "Mobile Phone";

	}

	public String getCategory() {
		return "Mobile Devices";

	}

	public String getSubCaegory() {
		return "Smart Phonne";

	}

	/*
	 * running thread
	 */
	public void startFetchingImageInfo(ImageCapture imageCapture) {
		
		//sets all the information
	}

	public CharSequence getDescription() {
		// TODO Auto-generated method stub
		return "Mobile Phone";
	}
}
