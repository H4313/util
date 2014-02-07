package com.h4313.deephouse.actuator;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorSet;
import com.h4313.deephouse.util.Constant;

public abstract class Actuator<T> {
	protected String id;
	protected ActuatorType type;
	// todo : maybe a list (synchronized)
	protected SensorSet sensors;
	protected boolean modified;

	public Actuator(String id, ActuatorType type) {
		this.id = id;
		sensors = new SensorSet();
		this.modified = false;
	}

	public String getFrame() {
		String frame = "";
		// TODO ecriture des trames
		frame += type.getTypeFrame();
		frame += getDatas();
		frame += id;
		frame += Constant.FRAME_STATUS_AND_CHECKSUM;
		return frame;
	}

	protected abstract String getDatas();
	
	public abstract void update(Frame frame) throws DeepHouseException;
	public abstract void setUserDesiredValue(String value) throws DeepHouseException;
	
	public abstract T getLastValue();

	public abstract void setLastValue(T o);
	
	public String toString() {
		String text = type+" actuator / Value: "+dataString();
		return text;
	}
	
	protected abstract String dataString();
	
	public boolean getModified() {
		return modified;
	}
	
	public void setModified(boolean modified) {
		this.modified = modified;
	}
}
