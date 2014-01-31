package com.h4313.deephouse.housemodel;

import java.io.Serializable;

import javax.persistence.Entity;


@Entity
public class LivingRoom extends Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LivingRoom(int id) {
		super(id);
	}

}
