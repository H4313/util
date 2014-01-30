package com.h4313.deephouse.housemodel;

import com.h4313.deephouse.actuator.ActuatorSet;
import com.h4313.deephouse.sensor.SensorSet;

//QUestion: ca sert a qlq chose a sous-classer room:-?
public abstract class Room {
	protected int idRoom;
	protected SensorSet sensors;
	protected ActuatorSet actuators;
	
	public Room(int id) {
		this.idRoom = id;
		this.sensors = new SensorSet();
		this.actuators = new ActuatorSet();
	}
	
}
