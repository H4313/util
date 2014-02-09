package com.h4313.deephouse.sensor;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorType;
import com.h4313.deephouse.util.Constant;

@Entity
public abstract class Sensor<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String id;
	protected SensorType type;
	protected  Map<String,Actuator<Object>> actuators;
	
	@Transient
	public String getFrame() {
		String frame = "";
		frame += type.getTypeFrame();
		frame += getDatas();
		frame += id;
		frame += Constant.FRAME_STATUS_AND_CHECKSUM;
		return frame;
	}

	public abstract void update(Frame frame) throws DeepHouseException;
	
	@Column
	public abstract T getLastValue();

	public abstract void setLastValue(T o);
	
	
	@Transient
	protected abstract String getDatas();
	
	@Id
	@Column(name="id", nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="sensortype", nullable=false)
	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}
	
	public String toString() {
		String text = type.toString()+" sensor / Value: "+dataString();
		return text;
	}
	
	protected abstract String dataString();
	
	@OneToMany(cascade = CascadeType.ALL)
	@MapKey(name = "id")
	public Map<String, Actuator<Object>> getActuators() {
		return actuators;
	}
	

}
