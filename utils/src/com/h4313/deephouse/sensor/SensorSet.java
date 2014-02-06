package com.h4313.deephouse.sensor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.actuator.ActuatorType;
import com.h4313.deephouse.exceptions.DeepHouseDuplicateException;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseNotFoundException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorType;

//Hashtable is synchronized
public class SensorSet extends Hashtable<String, Sensor<Object>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8523495607779653300L;

	public SensorSet() {
		super();
	}

	public void addSensor(String id, SensorType type) throws DeepHouseException {
		if (this.get(id) != null) {
			throw new DeepHouseDuplicateException("Id " + id + "already taken");
		}
		this.put(id, SensorFactory.createSensor(id, type));
	}

	public void updateSensor(Frame frame) throws DeepHouseException {
		Sensor<Object> sensor = this.get(frame.getId());
		if (sensor == null) {
			throw new DeepHouseNotFoundException("Id " + frame.getId()
					+ "not found");
		}
		sensor.update(frame);
	}
	
	/**
	 * Retourne la liste des sensors d'un certain type
	 */
	public ArrayList<Sensor<Object>> getByType(SensorType type) {
		ArrayList<Sensor<Object>> list = new ArrayList<Sensor<Object>>();
		Enumeration<Sensor<Object>> e = this.elements();
		Sensor<Object> a;

		while (e.hasMoreElements()) {
			a = e.nextElement();
			if (a.type == type) {
				list.add(a);
			}
		}
		return list;
	}

}
