package com.h4313.deephouse.frame;

import com.h4313.deephouse.exceptions.DeepHouseException;
import com.h4313.deephouse.exceptions.DeepHouseFormatException;

public class Frame {

	private static final int frameLength = 28;

	private String id;
	private String type;
	private int[] dataArr;
	private String dataStr;
	private String status;
	private String checkSum;

	/**
	 * @throws Exception
	 *             Constructor for a Frame: parse the given string into a Frame
	 *             object
	 * 
	 * @param the
	 *            frame read, represented as a string
	 * @throws
	 * */
	public Frame(String frameStr) throws DeepHouseException {
		if (frameStr.length() != frameLength) {
			throw new DeepHouseFormatException(
					"The frame's lenght doesn't respect standards");
		}

		this.type = frameStr.substring(0, 8);
		this.dataStr = frameStr.substring(8, 16);
		this.id = frameStr.substring(16, 24);
		this.status = frameStr.substring(24, 26);
		this.checkSum = frameStr.substring(26, 28);

		this.dataArr = new int[4];
		for (int i = 8; i < 16; i = i + 2) {
			dataArr[(i - 8) / 2] = Integer.parseInt(
					frameStr.substring(i, i + 2), 16);
		}
	}

	/********** getters *************/

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public int[] getDataArr() {
		return dataArr;
	}

	public String getDataStr() {
		return dataStr;
	}

}
