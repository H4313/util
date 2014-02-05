package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionOfElements;
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

	private static volatile House instance = null;	
	
	protected List<Room> rooms;


	protected int idHouse;

	private House() {
		this.idHouse = 0;
		rooms = new ArrayList<Room>();
		for (int i = 0; i < RoomConstants.NB_PIECES; i++) {
			rooms.add(RoomFactory.createInstance(i));
		}
	}

	/**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static House getInstance() {
        if (House.instance == null) {
           synchronized(House.class) {
             if (House.instance == null) {
            	 House.instance = new House();
             }
           }
        }
        return House.instance;
    }

	/**
	 * If the room exists => get room
	 * 
	 * if not => create room
	 * */
	public Room getOrAddRoom(int type) throws DeepHouseException {
		for (Room r : rooms) {
			if (r.idRoom == type) {
				return r;
			}
		}
		Room newRoom = RoomFactory.createInstance(type);
		this.rooms.add(newRoom);
		return newRoom;
	}
	



	/**
	 * JSON Structure
	 * 
	 * �piece� : �numPiece�, �capteur� : �idCapteur�, �type � : �typeCapteur�
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


	@OneToMany
	public List<Room> getRooms() {
		return rooms;
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

	
	
	public void setRooms(List<Room> rooms) {
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
