package com.h4313.deephouse.sensor;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorType;
import com.h4313.deephouse.util.Constant;

public class TemperatureSensor extends Sensor {

	protected Double lastValue;
	
	public TemperatureSensor(String id, SensorType type) {
		this.id = id;
		this.type = type;
	}

	public String getDatas() {
		String datas = "";
		for(int i=0 ; i<4-Constant.SENSOR_TEMPERATURE_BYTES ; i++) {
			datas += "00";
		}
		//marche pas le toHexString
		datas += Double.toHexString(50*Math.pow(2, 8*Constant.SENSOR_TEMPERATURE_BYTES)/(Constant.SENSOR_TEMPERATURE_MAX - Constant.SENSOR_TEMPERATURE_MIN) + Constant.SENSOR_TEMPERATURE_MIN);
		return datas;
	}

	@Override
	public void update(Frame frame) throws DeepHouseException {
		// TODO Auto-generated method stub
		
	}
}
