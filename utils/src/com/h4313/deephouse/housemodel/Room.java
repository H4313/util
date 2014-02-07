package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.h4313.deephouse.actuator.*;
import com.h4313.deephouse.exceptions.DeepHouseDuplicateException;
import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.actuator.ActuatorSet;
import com.h4313.deephouse.actuator.ActuatorType;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseNotFoundException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.*;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.sensor.Sensor;
import com.h4313.deephouse.sensor.SensorSet;
import com.h4313.deephouse.sensor.SensorType;


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
		this.sensors =  new Hashtable<String, Sensor<Object>>();
		this.actuators = new Hashtable<String, Actuator>();
	}

	public Room() {
		
	}


	@SuppressWarnings("unchecked")
	public void userAction(String action, String value)
			throws DeepHouseException {

//		ArrayList<Actuator<Object>> list;
//		if (RoomConstants.tempAction.equals(action)) {
//			list = actuators.getByType(ActuatorType.RADIATOR);
//		} else if (RoomConstants.humAction.equals(action)) {
//			list = actuators.getByType(ActuatorType.HUMIDITYCONTROL);
//		} else if (RoomConstants.lightAction.equals(action)) {
//			list = actuators.getByType(ActuatorType.LIGHTCONTROL);
//		} else if (RoomConstants.wind1Action.equals(action)) {
//			list = actuators.getByType(ActuatorType.WINDOWCLOSER_1);
//		} else if (RoomConstants.wind2Action.equals(action)) {
//			list = actuators.getByType(ActuatorType.WINDOWCLOSER_2);
//		} else if (RoomConstants.flapAction.equals(action)) {
//			list = actuators.getByType(ActuatorType.FLAPCLOSER);
//		} else {
//			throw new DeepHouseFormatException("Unknown action type : " +action);
//		}
//		
//		for(Actuator act : list){
//			act.setUserDesiredValue(value);
//		}
	}

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

		//this.addSensor(id, type);
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

	/**
	 * Retourne la liste des sensors d'un certain type
	 */
	public ArrayList<Sensor<Object>> getSensorByType(SensorType type) {
//		ArrayList<Sensor<Object>> list = new ArrayList<Sensor<Object>>();
//		Enumeration<Sensor<Object>> e = sensors.elements();
//		Sensor<Object> a;
//
//		while (e.hasMoreElements()) {
//			a = e.nextElement();
//			if (a.getType()== type) {
//				list.add(a);
//			}
//		}
//		return list;
		return null;
	}


	


	
	

}
