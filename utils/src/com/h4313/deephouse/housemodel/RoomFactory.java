package com.h4313.deephouse.housemodel;


public abstract class RoomFactory {

	public static Room createInstance(int type) {

		switch (type) {
		case RoomConstants.ID_BEDROOM:
			return new Bedroom(type);
		case RoomConstants.ID_OFFICE:
			return new Office(type);
		case RoomConstants.ID_CORRIDOR:
			return new Corridor(type);
		case RoomConstants.ID_KITCHEN:
			return new Kitchen(type);
		case RoomConstants.ID_LIVING_ROOM:
			return new LivingRoom(type);
		case RoomConstants.ID_BATHROOM:
			return new Bathroom(type);
		}
		//throw new DeepHouseFormatException("Unknown Room Id");
		//normally unreached 
		return new LivingRoom(type);
	}
}
