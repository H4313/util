package com.h4313.deephouse.actuator;

public enum ActuatorType {
	RADIATOR(false,"B55A1005","Radiator"),
	LIGHTCONTROL(false,"","Light control"),
	CLIMA(false,"00000000","Air conditioner");
	
	/*
	 * true if the actuator is a physical one false if it's a virtual one
	 */
	private final boolean physical;
	
	//typeFrame has to be 4 byte long => 8 hexa characters
	private final String typeFrame;
	
	private final String name;

	ActuatorType(boolean physical, String typeFrame, String name) {
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
}
