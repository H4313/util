package com.h4313.deephouse.util;

public abstract class Constant
{
	public static final String FRAME_STATUS_AND_CHECKSUM = "30B3";

	public static final String SENSOR_BOOLEAN_DATAS_TRUE = "10000000";
	
	public static final String SENSOR_BOOLEAN_DATAS_FALSE = "00000000";
	
	public static final Double SENSOR_TEMPERATURE_MIN = 0.0;
	
	public static final Double SENSOR_TEMPERATURE_MAX = 50.0;
	
	public static final int SENSOR_TEMPERATURE_BYTES = 2;
	
	/**
	 * TCP
	 */
	public static final int TCP_FRAME_LENGTH = 28;
}
