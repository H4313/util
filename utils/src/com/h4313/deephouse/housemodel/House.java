package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.json.JSONObject;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorType;

@Entity
public class House implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name="rooms", nullable=false)
	protected ArrayList<Room> rooms;

	protected int idHouse;
	
	public House() {
		rooms = new ArrayList<Room>();
		this.idHouse=0;
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
	
	
	public void updateSensor(Frame frame) throws DeepHouseException {
		for(Room r : rooms) {
			if(r.sensors.containsValue(frame.getId())) {
				r.sensors.updateSensor(frame);
				return;
			}
		}
	}
	
	@Column(name="rooms", nullable=true)
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	@Id
	@Column(name="idhouse", nullable=false)
	public int getIdHouse() {
		return idHouse;
	}

	public void setIdHouse(int idHouse) {
		this.idHouse = idHouse;
	}
	
	
	
	
}
