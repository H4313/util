
package com.h4313.deephouse.util;

import org.hibernate.*;
import org.hibernate.cfg.*;

import com.h4313.deephouse.housemodel.Bathroom;
import com.h4313.deephouse.housemodel.Bedroom;
import com.h4313.deephouse.housemodel.Corridor;
import com.h4313.deephouse.housemodel.House;
import com.h4313.deephouse.housemodel.Kitchen;
import com.h4313.deephouse.housemodel.LivingRoom;
import com.h4313.deephouse.housemodel.Office;
import com.h4313.deephouse.housemodel.Room;
import com.h4313.deephouse.sensor.*;
import com.h4313.deephouse.actuator.*;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
			.addPackage("com.h4313.deephouse.sensor") // le nom complet du package
			.addAnnotatedClass(BooleanSensor.class)
			.addAnnotatedClass(TemperatureSensor.class)
			.addPackage("com.h4313.deephouse.actuator") // le nom complet du package
			.addAnnotatedClass(Radiator.class)
			.addAnnotatedClass(BooleanActuator.class)
			.addPackage("com.h4313.deephouse.housemodel") // le nom complet du package
			.addAnnotatedClass(House.class)
			.addAnnotatedClass(Room.class)
			.addAnnotatedClass(Office.class)
			.addAnnotatedClass(Bedroom.class)
			.addAnnotatedClass(LivingRoom.class)
			.addAnnotatedClass(Kitchen.class)
			.addAnnotatedClass(Corridor.class)
			.addAnnotatedClass(Bathroom.class)
			.buildSessionFactory();
		} catch (Throwable ex) {
			// Log exception!
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

}
