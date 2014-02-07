package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.h4313.deephouse.exceptions.*;
import com.h4313.deephouse.sensor.*;


public class SensorDAO extends DAO<Sensor<Object>> {

	@SuppressWarnings("unchecked")
	@Override
	public Sensor<Object> find(Object id) throws DeepHouseTypeException {
		
		if (!(id instanceof String)) {
			throw new DeepHouseTypeException("Object is not good type, except : String");
		}
		return (Sensor<Object>) session.get(Sensor.class,(String) id );
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sensor<Object>> findAll() {
			return session.createQuery("FROM "+Sensor.class.getName()).list();
	}

	@Override
	public Sensor<Object> createUpdate(Sensor<Object> obj){
		Transaction transaction = null;
		try {
			
			transaction = session.beginTransaction();		
			session.saveOrUpdate(obj);
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return obj;
		
	}

	@Override
	public void delete(Sensor<Object> obj) {
		Transaction transaction = null;
		try {
			
			transaction = session.beginTransaction();		
			session.delete(obj);
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
	
	}





	

}
