package com.h4313.deephouse.actuator;

import javax.persistence.Transient;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.util.Constant;

public class BooleanActuator extends Actuator<Boolean> {

	private static final long serialVersionUID = 1L;

	protected boolean lastValue;

	protected String trueText;

	protected String falseText;

	public BooleanActuator(String id, ActuatorType type, String falseText,
			String trueText) {
		super(id, type);
		this.trueText = trueText;
		this.falseText = falseText;
	}

	@Transient
	protected String getDatas() {
		String datas = "";
		if (lastValue) {
			datas += Constant.SENSOR_BOOLEAN_DATAS_TRUE;
		} else {
			datas += Constant.SENSOR_BOOLEAN_DATAS_FALSE;
		}
		return datas;
	}

	@Override
	public void update(Frame frame) throws DeepHouseException {
		if (frame.getDataStr().equals(Constant.SENSOR_BOOLEAN_DATAS_TRUE)) {
			lastValue = true;
		} else {
			lastValue = false;
		}
	}

	@Override
	public void setUserDesiredValue(String value) throws DeepHouseException {
		try {
			Boolean desiredValue = Boolean.parseBoolean(value);

			// TODO
		} catch (Exception e) {
			throw new DeepHouseFormatException("Format exception: " + value);
		}
	}

	public Boolean getLastValue() {
		return lastValue;
	}

	public void setLastValue(Boolean value) {
		this.lastValue = value;
	}

	@Override
	protected String dataString() {
		if (lastValue) {
			return trueText;
		} else {
			return falseText;
		}
	}

}
