package com.h4313.deephouse.sensor;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorType;

public class BooleanSensor extends Sensor {
	
	private static final String booleanSensorDatasTrue = "10000000";
	
	private static final String booleanSensorDatasFalse = "00000000";

	public BooleanSensor(String id, SensorType type) {
		this.id = id;
		this.type = type;
	}
	
	protected boolean lastValue;

	public String getDatas() {
		String datas = "";
		if(lastValue) {
			datas += booleanSensorDatasTrue;
		}
		else {
			datas += booleanSensorDatasFalse;
		}
		return datas;
	}

	@Override
	public void update(Frame frame) throws DeepHouseException {
		// TODO Auto-generated method stub
		if(frame.getDataStr().equals(booleanSensorDatasTrue)) {
			lastValue = true;
		}
		else {
			lastValue = false;
		}
	}
	
	public boolean getLastValue() {
		return lastValue;
	}
}
