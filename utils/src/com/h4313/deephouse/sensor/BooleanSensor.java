package com.h4313.deephouse.sensor;

import java.io.Serializable;

import javax.persistence.Transient;

import javax.persistence.*;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorType;
import com.h4313.deephouse.util.Constant;


@Entity
public class BooleanSensor extends Sensor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected boolean lastValue;
	
	public BooleanSensor(){
		
	}
	
	public BooleanSensor(String id, SensorType type) {
		this.id = id;
		this.type = type;
		this.lastValue=false;
	}
	
	
	
	@Transient
	public String getDatas() {
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
		// TODO Auto-generated method stub
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
	
	
}
