package com.h4313.deephouse.housemodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.h4313.deephouse.actuator.ActuatorSet;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.sensor.SensorSet;
import com.h4313.deephouse.sensor.SensorType;

@Entity
public abstract class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int idRoom;

	protected SensorSet sensors;

	protected ActuatorSet actuators;

	public Room(int id) {
		this.idRoom = id;
		this.sensors = new SensorSet();
		this.actuators = new ActuatorSet();
	}

	public void addSensor(String id, SensorType type) throws DeepHouseException {
		this.sensors.addSensor(id, type);
	}

	@Id
	@Column(name = "idRoom", nullable = false)
	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	@Column(name = "sensors", nullable = true)
	public SensorSet getSensors() {
		return sensors;
	}

	public void setSensors(SensorSet sensors) {
		this.sensors = sensors;
	}

	
	@Column(name="actuators", nullable=true)
	public ActuatorSet getActuators() {
		return actuators;
	}

	public void setActuators(ActuatorSet actuators) {
		this.actuators = actuators;
	}

}
