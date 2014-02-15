package com.h4313.deephouse.actuator;

public enum ActuatorType {
	// all actuators are virtual?
	RADIATOR(false, "B55A1001", "Radiator"), 
	WINDOWCLOSER(false, "B55A1002", "Window closer"),
	LIGHTCONTROL(false, "B55A1003",	"Light control"),
	DOORCONTROL(false, "B55A1004", "Door control"),
	FLAPCLOSER(false, "B55A1005", "Flap closer");
	

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
