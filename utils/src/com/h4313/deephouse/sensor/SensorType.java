package com.h4313.deephouse.sensor;

public enum SensorType {

	TEMPERATURE(true, "A55A1001", "Temperature"), 
	WINDOW(true, "A55A1002","Window state"), 
	LIGHT(true, "A55A1003", "Light state"), 
	DOOR(false, "A55A1004", "Door state"),
	FLAP(false, "A55A1005", "Flap state"),
	PRESENCE(true, "A55A1006", "Presence");

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
