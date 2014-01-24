package com.h4313.deephouse.housemodel;

import com.h4313.deephouse.actuator.ActuatorSet;
import com.h4313.deephouse.sensor.SensorSet;


public abstract class Room {
	protected int idRoom;
	
	protected SensorSet sensors;
	
	protected ActuatorSet actuators;
}
