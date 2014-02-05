package com.h4313.deephouse.housemodel;

import javax.persistence.Entity;

@Entity
public class Kitchen extends Room {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Kitchen (){
		super();
	}
	
	public Kitchen(int id) {
		super(id);
	}

}
