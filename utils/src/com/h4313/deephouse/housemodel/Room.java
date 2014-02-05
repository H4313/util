package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.h4313.deephouse.actuator.*;
import com.h4313.deephouse.exceptions.DeepHouseDuplicateException;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseNotFoundException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.*;

@Entity
public abstract class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int idRoom;

	protected Map<String,Sensor<Object>> sensors;
	protected Map<String,Actuator> actuators;

	public Room(int id) {
		this.idRoom = id;
		this.sensors =  new HashMap<String, Sensor<Object>>();
		this.actuators = new HashMap<String, Actuator>();
	}

	public Room() {
		
	}

	@SuppressWarnings("unchecked")
	public void addSensor(String id, SensorType type) throws DeepHouseException {
		this.sensors.put(id, SensorFactory.createSensor(id, type));
	}
	
	public void updateSensor(Frame frame) throws DeepHouseException {
		Sensor<Object> sensor = sensors.get(frame.getId());
		if (sensor == null) {
			throw new DeepHouseNotFoundException("Id " + frame.getId()
					+ "not found");
		}
		sensor.update(frame);
	}
	
	
	public void addActuator(String id, ActuatorType type) throws DeepHouseException {
		if (actuators.get(id) != null) {
			throw new DeepHouseDuplicateException("Id " + id + "already taken");
		}
		actuators.put(id,  ActuatorFactory.createActuator(id, type));
	}
	
	public void updateActuator(Frame frame) throws DeepHouseException {
		Actuator actuator = actuators.get(frame.getId());
		if (actuator == null) {
			throw new DeepHouseNotFoundException("Id " + frame.getId()
					+ "not found");
		}
		actuator.update(frame);
	}

	
	@Id
	@Column(name = "idRoom", nullable = false)
	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@MapKey(name = "id")
	public Map<String, Sensor<Object>> getSensors() {
		return sensors;
	}

	public void setSensors(Map<String, Sensor<Object>> sensors) {
		this.sensors = sensors;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@MapKey(name = "id")
	public Map<String, Actuator> getActuators() {
		return actuators;
	}

	public void setActuators(Map<String, Actuator> actuators) {
		this.actuators = actuators;
	}




	


	
	

}
