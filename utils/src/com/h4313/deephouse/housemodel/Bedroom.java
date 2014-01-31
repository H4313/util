package com.h4313.deephouse.housemodel;

import java.io.Serializable;

import javax.persistence.Entity;


@Entity
public class Bedroom extends Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2991121415163550046L;

	public Bedroom(int id) {
		super(id);
	}

}
