package com.h4313.deephouse.housemodel;


public abstract class RoomFactory {

	public static Room createInstance(int type) {

		switch (type) {
		case RoomConstants.ID_CHAMBRE1:
			return new Bedroom(type);
		case RoomConstants.ID_CHAMBRE2:
			return new Bedroom(type);
		case RoomConstants.ID_COULOIR:
			return new Corridor(type);
		case RoomConstants.ID_CUISINE:
			return new Kitchen(type);
		case RoomConstants.ID_SALON:
			return new LivingRoom(type);
		case RoomConstants.ID_WC_SALLEBAIN:
			return new Bathroom(type);
		}
		//throw new DeepHouseFormatException("Unknown Room Id");
		//normally unreached 
		return new LivingRoom(type);
	}
}
