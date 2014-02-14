package com.h4313.deephouse.actuator;

import com.h4313.deephouse.sensor.SensorType;

public enum ActuatorType {
	// all actuators are virtual?
	RADIATOR(false, "B55A1005", "Radiator"), 
	WINDOWCLOSER(false, "B55A0105", "Window closer"),
	LIGHTCONTROL(false, "B55A0005",	"Light control"),
	DOORCONTROL(false, "B55A0012", "Door control"),
	FLAPCLOSER(false, "B55A0010", "Flap closer");
	
	// HUMIDITYCONTROL(false, "00000000", "Humidity control")
	//AIRCONDITION(false, "B55A1105","Air conditioner")
	

	/*
	 * true if the actuator is a physical one false if it's a virtual one
	 */
	private final boolean physical;

	// typeFrame has to be 4 byte long => 8 hexa characters
	private final String typeFrame;

	private final String name;

	ActuatorType(boolean physical, String typeFrame, String name) {
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
