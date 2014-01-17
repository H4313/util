package com.h4313.deephouse.sensor;

public enum SensorType {

	TEMPERATURE(true), SWITCH(true), LIGHT(true), PRESENCE(true), HUMIDITY(
			false), NOISE(false), SMOKE(false), SMELL(false);

	/*
	 * true if the sensor is a physical one false if it's a virtual one
	 */
	private final boolean physical;

	SensorType(boolean physical) {
		this.physical = physical;
	}

	public boolean isPhysical() {
		return physical;
	}

}
