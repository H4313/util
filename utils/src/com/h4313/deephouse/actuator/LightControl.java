package com.h4313.deephouse.actuator;

import javax.persistence.Transient;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.util.Constant;

public class LightControl extends Actuator {
	
	private static final long serialVersionUID = 1L;
	
	protected boolean lastValue;
	
	public LightControl(String id, ActuatorType type) {
		super(id,type);
	}
	
	
	
	@Transient
	protected String getDatas() {
		String datas = "";
		if(lastValue) {
			datas += Constant.SENSOR_BOOLEAN_DATAS_TRUE;
		}
		else {
			datas += Constant.SENSOR_BOOLEAN_DATAS_FALSE;
		}
		return datas;
	}

	@Override
	public void update(Frame frame) throws DeepHouseException {
		if(frame.getDataStr().equals(Constant.SENSOR_BOOLEAN_DATAS_TRUE)) {
			lastValue = true;
		}
		else {
			lastValue = false;
		}
	}
	
	public boolean getLastValue() {
		return lastValue;
	}

	public void setLastValue(boolean lastValue) {
		this.lastValue = lastValue;
	}
	
	@Override
	protected String dataString() {
		if(lastValue) {
			return "Lights are on";
		}
		else {
			return "Lights are off";
		}
	}

}
