package com.h4313.deephouse.util;

public abstract class Constant
{
	/**
	 * SENSOR & ACTUATORS
	 */
	public static final String FRAME_STATUS_AND_CHECKSUM = "30B3";

	public static final String SENSOR_BOOLEAN_DATAS_TRUE = "10000000";
	
	public static final String SENSOR_BOOLEAN_DATAS_FALSE = "00000000";
	
	public static final Double SENSOR_TEMPERATURE_MIN = 0.0;
	
	public static final Double SENSOR_TEMPERATURE_MAX = 50.0;
	
	public static final Double DEFAULT_TEMPERATURE = 15.0;
	
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
    public static final int DEFAULT_MONTH = 0;
    public static final int DEFAULT_DAY = 1;
    public static final int DEFAULT_HOUR = 0;
    public static final int DEFAULT_MINUTE = 0;
    public static final int DEFAULT_SECOND = 0;
//    public static final int TIME_FACTOR = 1440; // current time = real time * X
    public static final int TIME_FACTOR = 500; // current time = real time * X

    /**
     * URL
     */
    public static final String URL_SERVEUR = "localhost:8080/deepHouse";
    public static final String URL_WEB_SERVICE_HOUSEMODEL = "localhost:8080/deepHouse/rest/houseModel";
    public static final String URL_WEB_SERVICE_ADDSENSOR = "localhost:8080/deepHouse/rest/addSensor";
    public static final String URL_WEB_SERVICE_ADDACTUATOR = "localhost:8080/deepHouse/rest/addActuator";
    public static final String URL_WEB_SERVICE_USERACTION = "localhost:8080/deepHouse/rest/userAction";
    
    /**
     * Application Android
     */
//    public static final long MILLISECONDS_TILL_REFRESH = 10000; // 10 secondes // PAUL OUT : 2014.02.19
    public static final long MILLISECONDS_TILL_REFRESH = 5000; // 5 seconds
    public static final long MILLISECONDS_TILL_UNMUTING_TEMPERATURE_BUTTONS = 60*30*1000; 
    public static final int SENSOR_ID_LENGTH = 8;
    public static final double RELATIVE_TEMPERATURE_INCREASE_ON_USER_RQST = 2;
    public static final double RELATIVE_TEMPERATURE_DECREASE_ON_USER_RQST = -2;
}
