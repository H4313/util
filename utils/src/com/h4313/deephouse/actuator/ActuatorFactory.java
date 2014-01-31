package com.h4313.deephouse.actuator;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;

public class ActuatorFactory {

	public static Actuator createActuator(String id, ActuatorType type)
			throws DeepHouseException {
		switch (type) {
		case RADIATOR:

			return new Radiator(id,type);

		case AIRCONDITION:
			// a voir
			return new Radiator(id,type);
			
		case LIGHTCONTROL:
			return new LightControl(id,type);
		}
		throw new DeepHouseFormatException("Unknown Actuator type");
	}
}
