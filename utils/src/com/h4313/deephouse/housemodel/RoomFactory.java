package com.h4313.deephouse.housemodel;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;

public abstract class RoomFactory {

	//TODO apres avoir decide si on sous-classe Room
	public static Room createInstance(RoomType type) throws DeepHouseException {
		switch (type) {
		case ID_CHAMBRE1:
			return new Bedroom(type.getId());
		case ID_CHAMBRE2:
			return new Bedroom(type.getId());
		case ID_COULOIR:
			return null; //TODO
		case ID_CUISINE:
			return new Kitchen(type.getId());
		case ID_SALON:
			break; 
		case ID_WC_SALLEBAIN:
			break;
		default:
			break;

		}
		throw new DeepHouseFormatException("Unknown Room type");
	}
}
