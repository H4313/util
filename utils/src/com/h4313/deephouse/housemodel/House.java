package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.actuator.ActuatorType;
import com.h4313.deephouse.dao.HouseDAO;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.exceptions.DeepHouseNotFoundException;
import com.h4313.deephouse.exceptions.DeepHouseTypeException;
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

	private House(int u) {
		this.idHouse = 0;
		rooms = new ArrayList<Room>();
		for (int i = 0; i < RoomConstants.NB_PIECES; i++) {
			rooms.add(RoomFactory.createInstance(i));
		}
	}

	/**
	 * Ne pas utiliser !!! Uniquement pour Hibernate
	 */
	public House() {
	}

	/**
	 * Méthode permettant de renvoyer une instance de la classe Singleton
	 * 
	 * @return Retourne l'instance du singleton.
	 */
	public final static House getInstance() {
		if (House.instance == null) {
			synchronized (House.class) {
				if (House.instance == null) {
					House.instance = new House(0);
				}
			}
		}
		return House.instance;
	}

	public final static void setInstance(House h) {
		House.instance = h;
	}

	public final static void initInstance(HouseDAO houseDao)
			throws DeepHouseException {
		House.instance = houseDao.find(0);
		if (House.instance == null) {
			throw new DeepHouseNotFoundException("Cannot find House 0 in Db");
		}
	}

	/************** Getters and Setters ****************/

	@Id
	@Column(name = "idhouse", nullable = false)
	public int getIdHouse() {
		return idHouse;
	}

	public void setIdHouse(int idHouse) {
		this.idHouse = idHouse;
	}

	@OneToMany
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	/************** Methods (if there are no params, cannot start with get) ****************/

	/**
	 * JSON Structure
	 * 
	 * �piece� : �numPiece�, �capteur� : �idCapteur�, �type � : �typeCapteur�
	 * */
	public void addSensor(Integer roomId, String idSensor, String type)
			throws DeepHouseException {
		SensorType sensorType = SensorType.valueOf(type);

		if (sensorType instanceof SensorType) {
			Room r = rooms.get(roomId);
			r.addSensor(idSensor, (SensorType) sensorType);
		} else {
			throw new DeepHouseFormatException(
					"MalFormed JSON : unknown room or sensor type");
		}
	}

	/**
	 * JSON Structure
	 * 
	 * �piece� : �numPiece�, �idActuator� : �idCapteur�, �type � : �typeCapteur�
	 * */
	public void addActuator(Integer roomId, String idActuator, String type)
			throws DeepHouseException {
		ActuatorType actuatorType = ActuatorType.valueOf(type);

		if (actuatorType instanceof ActuatorType) {
			Room r = rooms.get(roomId);
			r.addActuator(idActuator, (ActuatorType) actuatorType);
		} else {
			throw new DeepHouseFormatException(
					"MalFormed JSON : unknown room or actuator type");
		}
	}

	/**
	 * JSONObject:
	 * 
	 * "piece" : idPiece "typeAction" : string (see RoomConstants "valeur" :
	 * valeurCapteur= string
	 * */
	public void userAction(Integer roomId, String typeAction, String value,
			String actuatorId) throws DeepHouseException {
		try {
			if (roomId < 0 || roomId >= RoomConstants.NB_PIECES) {
				throw new DeepHouseFormatException("Unknown room id : "
						+ roomId);
			}
			Room r = rooms.get(roomId);
			r.userAction(typeAction, value, actuatorId);
		} catch (Exception e) {
			throw new DeepHouseFormatException("MalFormed JSON : "
					+ e.getMessage());
		}
	}

	public Sensor<Object> updateSensor(Frame frame) throws DeepHouseException {
		for (Room r : rooms) {
			if (r.sensors.containsKey(frame.getId())) {
				r.updateSensor(frame);
				return r.sensors.get(frame.getId());
			}
		}
		return null;
	}

	public boolean updateSensor(SensorType sensorType, Object value) {
		boolean found = false;
		for (Room r : rooms) {
			Set<Map.Entry<String, Sensor<Object>>> set = r.getSensors()
					.entrySet();

			for (Map.Entry<String, Sensor<Object>> entry : set) {
				Sensor<Object> sensor = entry.getValue();

				if (sensor.getType().equals(sensorType)) {
					sensor.setLastValue(value);
					found = true;
				}
			}
		}
		return found;
	}

	public boolean updateSensor(int idRoom, SensorType sensorType, Object value) {
		boolean found = false;
		for (Room r : rooms) {
			if (r.getIdRoom() == idRoom) {
				Set<Map.Entry<String, Sensor<Object>>> set = r.getSensors()
						.entrySet();

				for (Map.Entry<String, Sensor<Object>> entry : set) {
					Sensor<Object> sensor = entry.getValue();

					if (sensor.getType().equals(sensorType)) {
						sensor.setLastValue(value);
						found = true;
					}
				}
			}
		}
		return found;
	}

	public Actuator<Object> updateActuator(Frame frame)
			throws DeepHouseException {
		for (Room r : rooms) {
			if (r.actuators.containsKey(frame.getId())) {
				r.updateActuator(frame);
				return r.actuators.get(frame.getId());
			}
		}
		return null;
	}

	public void printInformations() {
		System.out.println("House id : " + idHouse);
		for (Room r : rooms) {
			r.printInformations();
		}
	}

}
