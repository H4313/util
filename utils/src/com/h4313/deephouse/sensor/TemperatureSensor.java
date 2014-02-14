package com.h4313.deephouse.sensor;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.util.Constant;
import com.h4313.deephouse.util.DecToHexConverter;

@Entity
public class TemperatureSensor extends Sensor<Double> {

	
	private static final long serialVersionUID = 1L;
	
	
	protected Double lastValue;
	
	public TemperatureSensor() {
	}
	
	public TemperatureSensor(String id, SensorType type) {
		this.id = id;
		this.type = type;
		lastValue = Double.valueOf(Constant.DEFAULT_TEMPERATURE);
	}
	
	@Transient
	public Double getLastValue() {
		return this.lastValue;
	}

	public void setLastValue(Double lastValue) {
		this.lastValue = lastValue;
	}

	@Override
	@Transient
	protected String getDatas() {
		String datas = "";
		for(int i=0 ; i<Constant.FRAME_DATA_LENGTH_BYTES - Constant.SENSOR_TEMPERATURE_BYTES ; i++) {
			datas += "00";
		}
		
		int value = (int)Math.floor(lastValue * Math.pow(2, 8*Constant.SENSOR_TEMPERATURE_BYTES)
									/(Constant.SENSOR_TEMPERATURE_MAX - Constant.SENSOR_TEMPERATURE_MIN) 
									+ Constant.SENSOR_TEMPERATURE_MIN);
		String temp = DecToHexConverter.decToHex(value);
	    temp = temp.substring(Constant.SENSOR_TEMPERATURE_BYTES*2);
		datas += temp;
		return datas;
	}

	@Override
	public void update(Frame frame) throws DeepHouseException {
		String datas = frame.getDataStr();
		datas = datas.substring(Constant.SENSOR_TEMPERATURE_BYTES*2);
		int value = Integer.valueOf(datas,16);
		Double temp = (value - Constant.SENSOR_TEMPERATURE_MIN)
					  *(Constant.SENSOR_TEMPERATURE_MAX - Constant.SENSOR_TEMPERATURE_MIN)
					  /Math.pow(2, 8*Constant.SENSOR_TEMPERATURE_BYTES);
		this.lastValue = temp;
	}
	
	@Override
	protected String dataString() {
		return this.lastValue.toString();
	}

	@Override
	@Transient
	public Double getLastValuePersist() {
		return this.lastValue;
	}
	
	@Override
	public void setLastValuePersist(Double lastValuePersist){
		this.lastValue=lastValuePersist;	
	}
}
