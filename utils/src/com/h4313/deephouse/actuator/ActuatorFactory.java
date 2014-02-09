package com.h4313.deephouse.actuator;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;

public class ActuatorFactory {

	public static Actuator createActuator(String id, ActuatorType type)
			throws DeepHouseException {
		switch (type) {
		case RADIATOR:
			return new Radiator(id, type);

		case LIGHTCONTROL:
			return new BooleanActuator(id, type, "OFF", "ON");

		case FLAPCLOSER:
			// a voir si boolean
			return new BooleanActuator(id, type, "OFF", "ON");

		case WINDOWCLOSER:
			return new BooleanActuator(id, type, "Closed", "Opened");
			
		case DOORCONTROL:
			return new BooleanActuator(id, type, "Closed", "Opened");

		}
		throw new DeepHouseFormatException("Unknown Actuator type");
	}
}
