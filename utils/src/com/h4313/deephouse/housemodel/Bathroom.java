package com.h4313.deephouse.housemodel;

import javax.persistence.Entity;


@Entity
public class Bathroom extends Room {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public Bathroom (){
		super();
	}




	public Bathroom(int id) {
		super(id);
	}

}
