package com.h4313.deephouse.sensor;

public enum SensorType {

	TEMPERATURE(true,"A55A1005","Temperature"), 
	SWITCH(true,"A55A0B05","Switch"),
	LIGHT(true,"A55A0005","Light"), 
	PRESENCE(true,"A55A0105","Presence"), 
	HUMIDITY(false,"00000000","Humidity"), 
	NOISE(false,"00000000","Noise"), 
	SMOKE(false,"00000000","Smoke"), 
	SMELL(false,"00000000","Smell");

	/*
	 * true if the sensor is a physical one false if it's a virtual one
	 */
	private final boolean physical;
	
	//typeFrame has to be 4 byte long => 8 hexa characters
	private final String typeFrame;
	
	private final String name;

	SensorType(boolean physical, String typeFrame, String name) {
		this.typeFrame = typeFrame;
		this.physical = physical;
		this.name = name;
	}

	public boolean isPhysical() {
		return physical;
	}
	
	public String getTypeFrame() {
		return typeFrame;
	}
	
	public String toString() {
		return name;
	}

}
