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

		case SWITCH:
			return new BooleanSensor(id, type, "OFF", "ON");

		case SMELL:
			return new BooleanSensor(id, type, "", "");

		case SMOKE:
			return new BooleanSensor(id, type, "", "");

		case NOISE:
			return new BooleanSensor(id, type, "", "");

		case HUMIDITY:
			return new HumiditySensor(id, type);

		case TEMPERATURE:
			return new TemperatureSensor(id, type);
		}
		throw new DeepHouseFormatException("Unknown Sensor type");
	}
}
