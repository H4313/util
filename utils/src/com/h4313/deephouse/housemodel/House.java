package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.json.JSONObject;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.Sensor;

@Entity
public class House implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static volatile House instance = null;

	protected List<Room> rooms;

	protected int idHouse;

	private House(int u) {
		this.idHouse = 0;
		rooms = new ArrayList<Room>();
		for (int i = 0; i < RoomConstants.NB_PIECES; i++) {
			rooms.add(RoomFactory.createInstance(i));
		}
	}
	
	public House() {
		}

	/**
	 * Méthode permettant de renvoyer une instance de la classe Singleton
	 * @return Retourne l'instance du singleton.
	 */
	public final static House getInstance() {
		if (House.instance == null) {
			synchronized (House.class) {
				if (House.instance == null) {
					House.instance = new House();
				}
			}
		}
		return House.instance;
	}

//	/**
//	 * JSON Structure
//	 * 
//	 * �piece� : �numPiece�, �capteur� : �idCapteur�, �type � : �typeCapteur�
//	 * */
//	public void addSensor(JSONObject json) throws DeepHouseException {
//		Object sensorType;
//		int roomId;
//		String idSensor;
//		try {
//			roomId = json.getInt("piece");
//			idSensor = json.getString("capteur");
//			sensorType = json.get("type");
//		} catch (Exception e) {
//			throw new DeepHouseFormatException("MalFormed JSON "
//					+ e.getMessage());
//		}
//		if (sensorType instanceof SensorType) {
//			Room r = rooms.get(roomId);
//			r.sensors.addSensor(idSensor, (SensorType) sensorType);
//		} else {
//			throw new DeepHouseFormatException(
//					"MalFormed JSON : unknown room or sensor type");
//		}
//	}

	/**
	 * JSONObject:
	 * 
	 * "piece" : idPiece
	 * "typeAction" : string (see RoomConstants
	 * "valeur" : valeurCapteur= string
	 * */
	public void userAction(JSONObject json) throws DeepHouseException{
		try {
			int roomId = json.getInt("piece");
			String typeAction = json.getString("typeAction");
			String value = json.getString("valeur");
			
			if(roomId < 0 || roomId >= RoomConstants.NB_PIECES ){
				throw new DeepHouseFormatException("Unknown room id : " +roomId);
			}
			Room r = rooms.get(roomId);
			r.userAction(typeAction, value);
		} catch (Exception e) {
			throw new DeepHouseFormatException("MalFormed JSON : "
					+ e.getMessage());
		}
	}




	public Sensor<Object> updateSensor(Frame frame) throws DeepHouseException {
		for(Room r : rooms) {
			if(r.sensors.containsValue(frame.getId())) {
//				r.sensors.updateSensor(frame);
				return r.sensors.get(frame.getId());
			}
		}
		return null;
	}

	public Actuator updateActuator(Frame frame) throws DeepHouseException {

		for(Room r : rooms) {
			if(r.actuators.containsValue(frame.getId())) {
				r.updateActuator(frame);


				return r.actuators.get(frame.getId());
			}
		}
		return null;
	}


	
	@OneToMany
	public List<Room> getRooms() {
		return rooms;
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
