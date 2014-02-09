package com.h4313.deephouse.sensor;

import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.sensor.SensorType;

public class SensorFactory {

	public static Sensor createSensor(String id, SensorType type) throws DeepHouseFormatException {

		switch (type) {

		case LIGHT:
			return new BooleanSensor(id, type, "OFF", "ON");

		case PRESENCE:
			return new BooleanSensor(id, type, "Nobody in the room", "Someone in the room");

		case WINDOW:
			return new BooleanSensor(id, type, "CLOSE", "OPEN");

		case TEMPERATURE:
			return new TemperatureSensor(id, type);
			
		case DOOR:
			return new BooleanSensor(id, type, "CLOSE", "OPEN");
			
		case FLAP:
			return new BooleanSensor(id, type, "CLOSE", "OPEN");

		}
		throw new DeepHouseFormatException("Unknown Sensor type");
	}
}
