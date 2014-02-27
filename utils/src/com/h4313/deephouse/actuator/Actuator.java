package com.h4313.deephouse.actuator;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.h4313.deephouse.exceptions.DeepHouseDuplicateException;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseNotFoundException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.Sensor;
import com.h4313.deephouse.util.Constant;

@Entity
public abstract class Actuator<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String id;
	protected ActuatorType type;
	@ManyToMany(fetch = FetchType.EAGER)
	protected Map<String, Sensor<Object>> sensors;
	protected boolean modified;

	public Actuator(String id, ActuatorType type) {
		this.id = id;
		this.type = type;
		sensors = new Hashtable<String, Sensor<Object>>();
		this.modified = false;
	}

	public Actuator() {
	}

	/************** Getters and Setters ****************/
	// TODO apres discuter la diff setDesiredValue et setDesiredValuePersist??

	/************** Methods (if there are no params, cannot start with get) ****************/

	@Transient
	public String composeFrame() {
		String frame = "";
		// TODO ecriture des trames
		frame += type.getTypeFrame();
		frame += getDatas();
		frame += id;
		frame += Constant.FRAME_STATUS_AND_CHECKSUM;
		return frame;
	}

	@Transient
	protected abstract String getDatas();

	public abstract void update(Frame frame) throws DeepHouseException;

	public abstract void setDesiredValue(T value) throws DeepHouseException;

	@Transient
	public abstract T getDesiredValue();
	
	public abstract void setUserValue(T value) throws DeepHouseException;
	
	@Transient
	public abstract T getUserValue();
	
	@Column
	public abstract Double getUserValuePersist();
	
	public abstract void setUserValuePersist(Double desiredValuePersist);

	@Column
	public abstract Double getDesiredValuePersist();

	public abstract void setDesiredValuePersist(Double desiredValuePersist);

	@Transient
	public abstract T getLastValue();

	public abstract void setLastValue(T o);

	@Column
	public abstract Double getLastValuePersist();

	public abstract void setLastValuePersist(Double lastValuePersist);

	public String toString() {
		String text = type + " actuator(Id:) " + id + "  / Value: "
				+ dataString();
		return text;
	}

	protected abstract String dataString();

	public void addSensor(Sensor<Object> sensor) throws DeepHouseException {
		if (sensors.get(sensor.getId()) != null) {
			throw new DeepHouseDuplicateException("Id " + id + "already taken");
		}
		sensors.put(sensor.getId(), sensor);
	}

	public void updateSensor(Frame frame) throws DeepHouseException {
		Sensor<Object> sensor = sensors.get(frame.getId());
		if (sensor == null) {
			throw new DeepHouseNotFoundException("Id " + frame.getId()
					+ "not found");
		}
		sensor.update(frame);
	}

	@Column
	public boolean getModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	@Column
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column
	public ActuatorType getType() {
		return type;
	}

	public void setType(ActuatorType type) {
		this.type = type;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Map<String, Sensor<Object>> getSensors() {
		return sensors;
	}

	public void setSensors(Map<String, Sensor<Object>> sensors) {
		this.sensors = sensors;
	}

}
