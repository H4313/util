package com.h4313.deephouse.sensor;

import java.io.Serializable;

import javax.persistence.*;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorType;
import com.h4313.deephouse.util.Constant;


@Entity
public class BooleanSensor extends Sensor<Boolean> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String falseText;
	
	protected String trueText;
	

	protected Boolean lastValue;
	
	public BooleanSensor(String id, SensorType type, String falseText, String truetext) {
		this.id = id;
		this.type = type;
		this.lastValue = false;
		this.falseText = falseText;
		this.trueText = truetext;
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
		// TODO Auto-generated method stub
		if(frame.getDataStr().equals(Constant.SENSOR_BOOLEAN_DATAS_TRUE)) {
			lastValue = true;
		}
		else {
			lastValue = false;
		}
	}
	
	@Transient
	public Boolean getLastValue() {
		return lastValue;
	}

	public void setLastValue(Boolean lastValue) {
		this.lastValue = Boolean.valueOf(lastValue);
	}
	
	@Override
	protected String dataString() {
		if(lastValue) {
			return trueText;
		} 
		else {
			return falseText;
		}
	}


	@Column
	public String getFalseText() {
		return falseText;
	}



	public void setFalseText(String falseText) {
		this.falseText = falseText;
	}


	@Column
	public String getTrueText() {
		return trueText;
	}



	public void setTrueText(String trueText) {
		this.trueText = trueText;
	}
	
	
	
}
