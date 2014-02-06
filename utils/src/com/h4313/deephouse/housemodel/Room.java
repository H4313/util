package com.h4313.deephouse.housemodel;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.actuator.ActuatorSet;
import com.h4313.deephouse.actuator.ActuatorType;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.sensor.SensorSet;
import com.h4313.deephouse.sensor.SensorType;

@Entity
public abstract class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int idRoom;

	protected SensorSet sensors;

	protected ActuatorSet actuators;

	public Room(int id) {
		this.idRoom = id;
		this.sensors = new SensorSet();
		this.actuators = new ActuatorSet();
	}

	public Room() {
		// TODO Auto-generated constructor stub
	}

	public void userAction(String action, String value)
			throws DeepHouseException {
		ArrayList<Actuator> list;
		if (RoomConstants.tempAction.equals(action)) {
			list = actuators.getByType(ActuatorType.RADIATOR);
		} else if (RoomConstants.humAction.equals(action)) {
			list = actuators.getByType(ActuatorType.HUMIDITYCONTROL);
		} else if (RoomConstants.lightAction.equals(action)) {
			list = actuators.getByType(ActuatorType.LIGHTCONTROL);
		} else if (RoomConstants.wind1Action.equals(action)) {
			list = actuators.getByType(ActuatorType.WINDOWCLOSER_1);
		} else if (RoomConstants.wind2Action.equals(action)) {
			list = actuators.getByType(ActuatorType.WINDOWCLOSER_2);
		} else if (RoomConstants.flapAction.equals(action)) {
			list = actuators.getByType(ActuatorType.FLAPCLOSER);
		} else {
			throw new DeepHouseFormatException("Unknown action type : " +action);
		}
		
		for(Actuator act : list){
			act.setUserDesiredValue(value);
		}
	}

	public void addSensor(String id, SensorType type) throws DeepHouseException {
		this.sensors.addSensor(id, type);
	}
	
	public void addActuator(String id, ActuatorType type) throws DeepHouseException {
		this.actuators.addActuator(id, type);
	}

	@Id
	@Column(name = "idRoom", nullable = false)
	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	@Transient
	public SensorSet getSensors() {
		return sensors;
	}

	public void setSensors(SensorSet sensors) {
		this.sensors = sensors;
	}

	@Transient
	public ActuatorSet getActuators() {
		return actuators;
	}

	public void setActuators(ActuatorSet actuators) {
		this.actuators = actuators;
	}

}
