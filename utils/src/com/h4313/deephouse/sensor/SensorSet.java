package com.h4313.deephouse.sensor;


import java.util.Collection;
import java.util.HashMap;

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
public class SensorSet implements Map<String,Sensor<Object>> {

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
//		ArrayList<Sensor<Object>> list = new ArrayList<Sensor<Object>>();
//		Enumeration<Sensor<Object>> e = this.elements();
//		Sensor<Object> a;
//
//		while (e.hasMoreElements()) {
//			a = e.nextElement();
//			if (a.type == type) {
//				list.add(a);
//			}
//		}
//		return list;
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<String, Sensor<Object>>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor<Object> get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor<Object> put(String arg0, Sensor<Object> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Sensor<Object>> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sensor<Object> remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Sensor<Object>> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
