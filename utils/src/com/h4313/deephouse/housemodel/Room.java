package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.actuator.ActuatorFactory;
import com.h4313.deephouse.actuator.ActuatorType;
import com.h4313.deephouse.exceptions.DeepHouseDuplicateException;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.exceptions.DeepHouseNotFoundException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.Sensor;
import com.h4313.deephouse.sensor.SensorFactory;
import com.h4313.deephouse.sensor.SensorType;
import com.h4313.deephouse.util.Tool;

@Entity
public abstract class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int idRoom;
	
	@OneToMany(fetch = FetchType.EAGER)
	protected Map<String, Sensor<Object>> sensors;
	
	@OneToMany(fetch = FetchType.EAGER)
	protected Map<String, Actuator<Object>> actuators;

	public Room(int id) {
		this();
		this.idRoom = id;
	}

	public Room() {
		this.sensors = new Hashtable<String, Sensor<Object>>();
		this.actuators = new Hashtable<String, Actuator<Object>>();
	}

	/************** Getters and Setters ****************/

	@Id
	@Column(name = "idRoom", nullable = false)
	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@MapKey(name = "id")
	public Map<String, Actuator<Object>> getActuators() {
		return actuators;
	}

	public void setActuators(Map<String, Actuator<Object>> actuators) {
		this.actuators = actuators;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@MapKey(name = "id")
	public Map<String, Sensor<Object>> getSensors() {
		return sensors;
	}

	public void setSensors(Map<String, Sensor<Object>> sensors) {
		this.sensors = sensors;
	}

	/************** Methods (if there are no params, cannot start with get) ****************/
	@SuppressWarnings("unchecked")
	public void userAction(String action, String value)
			throws DeepHouseException {

		ArrayList<Actuator<Object>> list;
		if (RoomConstants.tempAction.equals(action)) {
			list = getActuatorByType(ActuatorType.RADIATOR);
		} else if (RoomConstants.lightAction.equals(action)) {
			list = getActuatorByType(ActuatorType.LIGHTCONTROL);
		} else if (RoomConstants.windAction.equals(action)) {
			list = getActuatorByType(ActuatorType.WINDOWCLOSER);
		} else if (RoomConstants.doorAction.equals(action)) {
			list = getActuatorByType(ActuatorType.DOORCONTROL);
		} else if (RoomConstants.flapAction.equals(action)) {
			list = getActuatorByType(ActuatorType.FLAPCLOSER);
		} else {
			throw new DeepHouseFormatException("Unknown action type : "
					+ action);
		}

		for (Actuator<Object> act : list) {
			if (RoomConstants.tempAction.equals(action)) {
				act.setUserValue(Double.valueOf(value));
			}
			else
			{
				act.setUserValue(Boolean.valueOf(value));
			}
		}
	}

	public void addSensor(String id, SensorType type) throws DeepHouseException {
		this.sensors.put(id, SensorFactory.createSensor(id, type));
	}

	public void updateSensor(Frame frame) throws DeepHouseException {
		Sensor<Object> sensor = sensors.get(frame.getId());
		if (sensor == null) {
			throw new DeepHouseNotFoundException("Id " + frame.getId()
					+ "not found");
		}
		sensor.update(frame);
	}

	public void addActuator(String id, ActuatorType type)
			throws DeepHouseException {
		if (actuators.get(id) != null) {
			throw new DeepHouseDuplicateException("Id " + id + "already taken");
		}
		actuators.put(id, ActuatorFactory.createActuator(id, type));
	}

	public void updateActuator(Frame frame) throws DeepHouseException {
		Actuator<Object> actuator = actuators.get(frame.getId());
		if (actuator == null) {
			throw new DeepHouseNotFoundException("Id " + frame.getId()
					+ "not found");
		}
		actuator.update(frame);

		// this.addSensor(id, type);
	}

	public void connectSensorActuator(String sensorId, String actuatorId)
			throws DeepHouseException {
		Sensor<Object> s = this.sensors.get(sensorId);
		if (s == null) {
			throw new DeepHouseNotFoundException("Inexistent sensor : "
					+ sensorId);
		}
		Actuator<Object> act = this.actuators.get(actuatorId);
		if (act == null) {
			throw new DeepHouseNotFoundException("Inexistent actuator : "
					+ actuatorId);
		}
		if (!Tool.testTypeConnectivity(s.getType(), act.getType())) {
			throw new DeepHouseNotFoundException(
					"Incompatible sensor - actuator types : " + act.getType()
							+ "-" + s.getType());
		}
		this.connectSensorActuator(s, act);
	}

	private void connectSensorActuator(Sensor<Object> s, Actuator<Object> act) {
		if (!act.getSensors().containsKey(s.getId())) {
			act.getSensors().put(s.getId(), s);
		}
	}

	/*
	 * establish logical connections between Sensors and Actuators - uses the
	 * rooms lists of sensors and actuators
	 */
	public void establishConnections() throws DeepHouseException {
		for(Actuator<Object> act : actuators.values()){
			for(Sensor<Object> s : sensors.values()){
				if(Tool.testTypeConnectivity(s.getType(), act.getType())){
					this.connectSensorActuator(s, act);
				}
			}
		}
	}

	public void printInformations() {
		System.out.println("Room " + this.idRoom + " :");
		if (actuators.isEmpty()) {
			System.out.println("No actuators");
		} else {
			System.out.println("Actuators");
			for(Actuator<Object> act : actuators.values()){
				System.out.println(act.toString());
				for(Sensor<Object> s : act.getSensors().values()){
					System.out.println("     " + s.toString());
				}
			}
		}
		if (sensors.isEmpty()) {
			System.out.println("No actuators");
		} else {
			System.out.println("\nSensors");
			for(Sensor<Object> s : sensors.values()){
				System.out.println(s.toString());
			}
		}
		System.out.println();
	}

	/**
	 * Retourne la liste des sensors d'un certain type
	 */
	public ArrayList<Sensor<Object>> getSensorByType(SensorType type) {
		ArrayList<Sensor<Object>> list = new ArrayList<Sensor<Object>>();
		Set<Map.Entry<String, Sensor<Object>>> set = sensors.entrySet();
		for (Map.Entry<String, Sensor<Object>> entry : set) {
			if (entry.getValue().getType() == type) {
				list.add(entry.getValue());
			}
		}
		return list;
	}

	/**
	 * Retourne la liste des actuators d'un certain type
	 */
	public ArrayList<Actuator<Object>> getActuatorByType(ActuatorType type) {
		ArrayList<Actuator<Object>> list = new ArrayList<Actuator<Object>>();
		Set<Map.Entry<String, Actuator<Object>>> set = actuators.entrySet();
		for (Map.Entry<String, Actuator<Object>> entry : set) {
			if (entry.getValue().getType() == type) {
				list.add(entry.getValue());
			}
		}
		return list;
	}
	
	/**
	 * Retourne le nom de la piece courante
	 * @return Nom de la piece
	 */
	@Transient
	public String getName()
	{
		String str = "Inconnue";
		switch(this.getIdRoom())
		{
		case RoomConstants.ID_LIVING_ROOM:
			str = "Salon";
			break;
		case RoomConstants.ID_KITCHEN:
			str = "Cuisine";
			break;
		case RoomConstants.ID_BATHROOM:
			str = "Salle de bain";
			break;
		case RoomConstants.ID_BEDROOM:
			str = "Chambre";
			break;
		case RoomConstants.ID_OFFICE:
			str = "Bureau";
			break;
		case RoomConstants.ID_CORRIDOR:
			str = "Couloir";
			break;
		}

		return str;
	}

	// PAUL OUT : 2014.02.20
//	public ArrayList<Actuator<Object>> getActuatorById(String id) {
//		ArrayList<Actuator<Object>> list = new ArrayList<Actuator<Object>>();
//		Set<Map.Entry<String, Actuator<Object>>> set = actuators.entrySet();
//		for (Map.Entry<String, Actuator<Object>> entry : set) {
//			if (entry.getValue().getType().equals(id)) {
//				list.add(entry.getValue());
//			}
//		}
//		return list;
//	}

}
