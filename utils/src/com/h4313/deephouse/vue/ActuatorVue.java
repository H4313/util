package com.h4313.deephouse.vue;

import com.h4313.deephouse.actuator.Actuator;

public class ActuatorVue {

	

	protected Actuator<Object> actuator;
	protected String nomPiece ;
	
	
	
	public Actuator<Object> getActuator() {
		return actuator;
	}
	public void setActuator(Actuator<Object> Actuator) {
		this.actuator = Actuator;
	}
	public String getNomPiece() {
		return nomPiece;
	}
	public void setNomPiece(String nomPiece) {
		this.nomPiece = nomPiece;
	}

	public ActuatorVue(Actuator<Object> actuator, String name) {
		super();
		this.actuator = actuator;
		this.nomPiece = name;
	}
	
	

}
