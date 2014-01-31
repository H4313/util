package com.h4313.deephouse.housemodel;

import java.io.Serializable;

import javax.persistence.Entity;


@Entity
public class Bedroom extends Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bedroom(int id) {
		super(id);
	}

}
