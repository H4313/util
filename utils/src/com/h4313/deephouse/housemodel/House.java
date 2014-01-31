package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.json.JSONObject;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.Sensor;
import com.h4313.deephouse.sensor.SensorType;

@Entity
public class House implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "rooms", nullable = false)
	protected ArrayList<Room> rooms;

	protected int idHouse;

	public House() {
		this.idHouse = 0;
		rooms = new ArrayList<Room>();
		for (int i = 0; i < RoomConstants.NB_PIECES; i++) {
			rooms.add(RoomFactory.createInstance(i));
		}
	}

	/**
	 * JSON Structure
	 * 
	 * “piece” : “numPiece”, “capteur” : “idCapteur”, “type “ : “typeCapteur”
	 * */
	public void addSensor(JSONObject json) throws DeepHouseException {
		Object sensorType;
		int roomId;
		String idSensor;
		try {
			roomId = json.getInt("piece");
			idSensor = json.getString("capteur");
			sensorType = json.get("type");
		} catch (Exception e) {
			throw new DeepHouseFormatException("MalFormed JSON "
					+ e.getMessage());
		}
		if (sensorType instanceof SensorType) {
			Room r = rooms.get(roomId);
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
	public Room getOrAddRoom(RoomType type) throws DeepHouseException {
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

	@Column(name = "rooms", nullable = true)
	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	@Id
	@Column(name = "idhouse", nullable = false)
	public int getIdHouse() {
		return idHouse;
	}

	public void setIdHouse(int idHouse) {
		this.idHouse = idHouse;
	}

}
