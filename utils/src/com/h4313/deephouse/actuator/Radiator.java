package com.h4313.deephouse.actuator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.util.Constant;
import com.h4313.deephouse.util.DecToHexConverter;



@Entity
public class Radiator extends Actuator<Double> {

	private static final long serialVersionUID = 1L;
	protected Double lastValue;

	public Radiator(String id, ActuatorType type) {
		super(id, type);
	}

	@Override
	@Transient
	protected String getDatas() {
		String datas = "";
		for (int i = 0; i < Constant.FRAME_DATA_LENGTH_BYTES
				- Constant.SENSOR_TEMPERATURE_BYTES; i++) {
			datas += "00";
		}

		int value = (int) Math
				.floor(lastValue
						* Math.pow(2, 8 * Constant.SENSOR_TEMPERATURE_BYTES)
						/ (Constant.SENSOR_TEMPERATURE_MAX - Constant.SENSOR_TEMPERATURE_MIN)
						+ Constant.SENSOR_TEMPERATURE_MIN);
		String temp = DecToHexConverter.decToHex(value);
		temp = temp.substring(Constant.SENSOR_TEMPERATURE_BYTES * 2);
		datas += temp;
		return datas;
	}

	@Override
	public void update(Frame frame) throws DeepHouseException {
		String datas = frame.getDataStr();
		datas = datas.substring(Constant.SENSOR_TEMPERATURE_BYTES * 2);
		int value = Integer.valueOf(datas, 16);
		Double temp = (value - Constant.SENSOR_TEMPERATURE_MIN)
				* (Constant.SENSOR_TEMPERATURE_MAX - Constant.SENSOR_TEMPERATURE_MIN)
				/ Math.pow(2, 8 * Constant.SENSOR_TEMPERATURE_BYTES);
		this.lastValue = temp;
	}

	@Override
	public void setUserDesiredValue(String value) throws DeepHouseException {
		try {
			Double desiredValue = Double.valueOf(value);
			
			// TODO 
		} catch (Exception e) {
			throw new DeepHouseFormatException("Format exception: " + value);
		}
	}


	

	
	


	@Override
	protected String dataString() {
		return lastValue.toString() + " °C";
	}

	public void setLastValue(Double value) {
		lastValue = value;
	}
	
	@Transient
	public Double getLastValue() {
		return lastValue;
	}

}
