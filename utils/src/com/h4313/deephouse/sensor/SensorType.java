package com.h4313.deephouse.sensor;

public enum SensorType {

	TEMPERATURE(true,"A55A1005"), 
	SWITCH(true,"A55A0B05"),
	LIGHT(true,"00000000"), 
	PRESENCE(true,"00000000"), 
	HUMIDITY(false,"00000000"), 
	NOISE(false,"00000000"), 
	SMOKE(false,"00000000"), 
	SMELL(false,"00000000");

	/*
	 * true if the sensor is a physical one false if it's a virtual one
	 */
	private final boolean physical;
	
	//typeFrame has to be 4 byte long => 8 hexa characters
	private final String typeFrame;

	SensorType(boolean physical, String typeFrame) {
		this.typeFrame = typeFrame;
		this.physical = physical;
	}

	public boolean isPhysical() {
		return physical;
	}
	
	public String getTypeFrame() {
		return typeFrame;
	}

}
