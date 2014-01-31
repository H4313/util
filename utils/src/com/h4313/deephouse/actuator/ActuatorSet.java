package com.h4313.deephouse.actuator;

import java.util.Hashtable;

import com.h4313.deephouse.exceptions.DeepHouseDuplicateException;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseNotFoundException;
import com.h4313.deephouse.frame.Frame;

public class ActuatorSet extends Hashtable<String, Actuator> {
	
	public ActuatorSet() {
		super();
	}

	public void addActuator(String id, ActuatorType type) throws DeepHouseException {
		if (this.get(id) != null) {
			throw new DeepHouseDuplicateException("Id " + id + "already taken");
		}
		this.put(id,  ActuatorFactory.createActuator(id, type));
	}
	
	public void updateActuator(Frame frame) throws DeepHouseException {
		Actuator actuator = this.get(frame.getId());
		if (actuator == null) {
			throw new DeepHouseNotFoundException("Id " + frame.getId()
					+ "not found");
		}
		actuator.update(frame);
	}
}
