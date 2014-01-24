package com.h4313.deephouse.housemodel;

import com.h4313.deephouse.server.sensor.SensorSet;
import com.h4313.deephouse.server.sensor.ActuatorSet;

public abstract class Room {
	protected int idRoom;
	
	protected SensorSet sensors;
	
	protected ActuatorSet actuators;
}
