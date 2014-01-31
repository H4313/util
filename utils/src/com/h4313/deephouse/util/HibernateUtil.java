package com.h4313.deephouse.util;

import org.hibernate.*;
import org.hibernate.cfg.*;

import com.h4313.deephouse.sensor.*;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
			.addPackage("com.h4313.deephouse.sensor") // le nom complet du package
			.addAnnotatedClass(BooleanSensor.class)
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