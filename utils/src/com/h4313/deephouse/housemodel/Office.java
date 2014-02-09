package com.h4313.deephouse.housemodel;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Office extends Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public Office(){
		super();
	}
	
	public Office(int id) {
		super(id);
	}

}
