package com.h4313.deephouse.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.h4313.deephouse.actuator.ActuatorType;
import com.h4313.deephouse.sensor.SensorType;

public abstract class Tool {
	public static boolean isValidIp(final String ip) {
		final String PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	public static boolean isValidPort(final String port) {
		final String PATTERN = "(?<=\\s|^)|\\d+(?=\\s|$)";

		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(port);
		return matcher.matches();
	}
	
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public static boolean testTypeConnectivity(SensorType sensorType,
			ActuatorType actType) {
		switch (actType) {
		case DOORCONTROL:
			return sensorType == SensorType.DOOR;
		case FLAPCLOSER:
			return sensorType == SensorType.FLAP;
		case LIGHTCONTROL:
			return sensorType == SensorType.LIGHT;
		case RADIATOR:
			return sensorType == SensorType.TEMPERATURE;
		case WINDOWCLOSER:
			return sensorType == SensorType.WINDOW;
		}
		return false;
	}
}
