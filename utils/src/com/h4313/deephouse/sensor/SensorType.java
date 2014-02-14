package com.h4313.deephouse.sensor;

public enum SensorType {

	TEMPERATURE(true, "A55A1005", "Temperature"), 
	WINDOW(true, "A55A0B05","Window state"), 
	LIGHT(true, "A55A0005", "Light state"), 
	DOOR(false, "A55A0006", "Door state"),
	FLAP(false, "A55A0007", "Flap state"),
	PRESENCE(true, "A55A0105", "Presence");
	// HUMIDITY(false,"A55A0106","Humidity"),
	// NOISE(false,"A55A0107","Noise"),
	// SMOKE(false,"A55A0108","Smoke"),
	// SMELL(false,"A55A0109","Smell");

	/*
	 * true if the sensor is a physical one false if it's a virtual one
	 */
	private final boolean physical;

	// typeFrame has to be 4 byte long => 8 hexa characters
	private final String typeFrame;

	private final String name;

	SensorType(boolean physical, String typeFrame, String name) {
		this.typeFrame = typeFrame;
		this.physical = physical;
		this.name = name;
	}

	public String getName() {
		return name;
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
