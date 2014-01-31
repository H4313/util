package com.h4313.deephouse.housemodel;

import java.util.ArrayList;

import org.json.JSONObject;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.Sensor;
import com.h4313.deephouse.sensor.SensorType;

public class House {

	protected ArrayList<Room> rooms;

	public House() {
		rooms = new ArrayList<Room>();
	}

	/**
	 * JSON Structure
	 * 
	 * “piece” : “numPiece”, “capteur” : “idCapteur”, “type “ : “typeCapteur”
	 * */
	public void addSensor(JSONObject json) throws DeepHouseException {
		Object roomType, sensorType;
		String idSensor;
		try {
			roomType = json.get("piece");
			idSensor = json.getString("capteur");
			sensorType = json.get("type");
		} catch (Exception e) {
			throw new DeepHouseFormatException("MalFormed JSON "
					+ e.getMessage());
		}
		if (roomType instanceof RoomType && sensorType instanceof SensorType) {
			Room r = this.getOrAddRoom((RoomType) roomType);
			r.sensors.addSensor(idSensor, (SensorType) sensorType);
		} else {
			throw new DeepHouseFormatException(
					"MalFormed JSON : unknown room or sensor type");
		}
	}

	/**
	 * If the room exists => get room
	 * 
	 * if not => create room
	 * */
	private Room getOrAddRoom(RoomType type) throws DeepHouseException {
		for (Room r : rooms) {
			if (r.idRoom == type.getId()) {
				return r;
			}
		}
		Room newRoom = RoomFactory.createInstance(type);
		this.rooms.add(newRoom);
		return newRoom;
	}
	
	
	public Sensor updateSensor(Frame frame) throws DeepHouseException {
		for(Room r : rooms) {
			if(r.sensors.containsValue(frame.getId())) {
				r.sensors.updateSensor(frame);
				return r.sensors.get(frame.getId());
			}
		}
		return null;
	}
	
	public Actuator updateActuator(Frame frame) throws DeepHouseException {
		for(Room r : rooms) {
			if(r.actuators.containsValue(frame.getId())) {
				r.actuators.updateActuator(frame);
				return r.actuators.get(frame.getId());
			}
		}
		return null;
	}
}
