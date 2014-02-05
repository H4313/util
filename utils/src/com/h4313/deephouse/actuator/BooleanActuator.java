package com.h4313.deephouse.actuator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.util.Constant;

@Entity
public class BooleanActuator extends Actuator {
	
	private static final long serialVersionUID = 1L;
	
	protected boolean lastValue;
	
	protected String trueText;
	
	protected String falseText;
	
	public BooleanActuator(String id, ActuatorType type, String falseText, String trueText) {
		super(id,type);
		this.trueText = trueText;
		this.falseText = falseText;
	}
	public BooleanActuator(){
		
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
	
	@Column
	public boolean getLastValue() {
		return lastValue;
	}

	public void setLastValue(boolean lastValue) {
		this.lastValue = lastValue;
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
	public String getTrueText() {
		return trueText;
	}
	
	public void setTrueText(String trueText) {
		this.trueText = trueText;
	}
	
	@Column
	public String getFalseText() {
		return falseText;
	}
	
	public void setFalseText(String falseText) {
		this.falseText = falseText;
	}
	

}
