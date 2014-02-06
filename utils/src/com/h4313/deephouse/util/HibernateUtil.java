package com.h4313.deephouse.util;

import org.hibernate.*;
import org.hibernate.cfg.*;

import com.h4313.deephouse.housemodel.Bathroom;
import com.h4313.deephouse.housemodel.Bedroom;
import com.h4313.deephouse.housemodel.Corridor;
import com.h4313.deephouse.housemodel.House;
import com.h4313.deephouse.housemodel.Kitchen;
import com.h4313.deephouse.housemodel.LivingRoom;
import com.h4313.deephouse.housemodel.Room;
import com.h4313.deephouse.sensor.*;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
			.addPackage("com.h4313.deephouse.sensor") // le nom complet du package
			.addAnnotatedClass(BooleanSensor.class)
			.addPackage("com.h4313.deephouse.housemodel") // le nom complet du package
			.addAnnotatedClass(House.class)
			.addAnnotatedClass(Room.class)
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