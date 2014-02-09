package com.h4313.deephouse.vue;

import com.h4313.deephouse.sensor.Sensor;

public class SensorVue {

	
	protected Sensor<Object> sensor;
	protected String nomPiece ;
	
	
	
	public Sensor<Object> getSensor() {
		return sensor;
	}
	public void setSensor(Sensor<Object> sensor) {
		this.sensor = sensor;
	}
	public String getNomPiece() {
		return nomPiece;
	}
	public void setNomPiece(String nomPiece) {
		this.nomPiece = nomPiece;
	}

	public SensorVue(Sensor<Object> value, String name) {
		super();
		this.sensor = value;
		this.nomPiece = name;
	}
	
	

}
