package com.h4313.deephouse.housemodel;

public enum RoomType {
	ID_SALON(0), ID_CUISINE(1), ID_WC_SALLEBAIN(2), ID_CHAMBRE1(3), ID_CHAMBRE2(
			4), ID_COULOIR(5);

	private final int id;

	private RoomType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
