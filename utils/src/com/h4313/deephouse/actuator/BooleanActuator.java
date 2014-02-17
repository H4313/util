package com.h4313.deephouse.actuator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.util.Constant;

@Entity
public class BooleanActuator extends Actuator<Boolean> {

	private static final long serialVersionUID = 1L;

	protected boolean lastValue;

	protected boolean desiredValue;

	protected String trueText;

	protected String falseText;

	// Uniquement pour Hibernate
	public BooleanActuator() {
	}

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
	public void setDesiredValue(Boolean value) throws DeepHouseException {
		try {
			desiredValue = value;

			// TODO
		} catch (Exception e) {
			throw new DeepHouseFormatException("Format exception: " + value);
		}
	}

	@Override
	@Transient
	public Boolean getDesiredValue() {
		return this.desiredValue;
	}

	@Transient
	public Boolean getLastValue() {
		return lastValue;
	}

	public void setLastValue(Boolean value) {
		this.lastValue = value;
	}

	@Override
	protected String dataString() {
		if (lastValue) {
			return (trueText != null) ? trueText : "true";
		} else {
			return (falseText != null) ? falseText : "false";
		}
	}

	@Override
	@Transient
	public Double getDesiredValuePersist() {

		if (this.desiredValue) {
			return (double) 1;
		} else {
			return (double) 0;
		}
	}

	@Override
	public void setDesiredValuePersist(Double desiredValuePersist) {
		if (desiredValuePersist == 1) {
			this.desiredValue = true;
		} else {
			this.desiredValue = false;
		}

	}

	@Override
	@Transient
	public Double getLastValuePersist() {
		if (this.lastValue) {
			return (double) 1.0;
		} else {
			return (double) 0.0;
		}
	}

	@Override
	public void setLastValuePersist(Double lastValuePersist) {
		if (lastValuePersist == 1.0) {
			this.lastValue = true;
		} else {
			this.lastValue = false;
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
