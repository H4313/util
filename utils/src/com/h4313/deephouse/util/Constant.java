package com.h4313.deephouse.util;

public abstract class Constant
{
	/**
	 * SENSOR
	 */
	public static final String FRAME_STATUS_AND_CHECKSUM = "30B3";

	public static final String SENSOR_BOOLEAN_DATAS_TRUE = "10000000";
	
	public static final String SENSOR_BOOLEAN_DATAS_FALSE = "00000000";
	
	public static final Double SENSOR_TEMPERATURE_MIN = 0.0;
	
	public static final Double SENSOR_TEMPERATURE_MAX = 50.0;
	
	public static final int SENSOR_TEMPERATURE_BYTES = 2;
	
	public static final int FRAME_DATA_LENGTH_BYTES = 4;
	
	/**
	 * TCP
	 */
	public static final int TCP_FRAME_LENGTH = 28;
	
	public static final int TCP_CONNECTION_TIMEOUT = 1000; // 1 second
	
	
    /**
     * DeepHouseCalendar
     */
    public static final int DEFAULT_YEAR = 2014;
    public static final int DEFAULT_MONTH = 1;
    public static final int DEFAULT_DAY = 1;
    public static final int DEFAULT_HOUR = 0;
    public static final int DEFAULT_MINUTE = 0;
    public static final int DEFAULT_SECOND = 0;
    public static final int TIME_FACTOR = 1440; // current time = real time * X
}
