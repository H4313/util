package com.h4313.deephouse.sensor;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.frame.Frame;
import com.h4313.deephouse.sensor.SensorType;

public class HumiditySensor extends Sensor {

	public HumiditySensor(String id, SensorType type) {
		this.id = id;
		this.type = type;
	}

	public String getDatas() {
		String datas = "";
		// TODO ecrire datas
		return datas;
	}

	@Override
	public void update(Frame frame) throws DeepHouseException {
		// TODO Auto-generated method stub
		
	}
}
