package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.h4313.deephouse.exceptions.DeepHouseTypeException;
import com.h4313.deephouse.sensor.Sensor;
import com.h4313.deephouse.util.HibernateUtil;


public class SensorDAO extends DAO<Sensor<Object>> {

	public SensorDAO()
	{
//		if(session == null)
//		{
//			session = HibernateUtil.getSession();
//		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Sensor<Object> find(Object id) throws DeepHouseTypeException {
		
		if (!(id instanceof String)) {
			throw new DeepHouseTypeException("Object is not good type, except : String");
		}
		
		Session session = HibernateUtil.getSession();
		Sensor<Object> sensor = (Sensor<Object>) session.get(Sensor.class,(String) id );
		session.close();
		
		return sensor;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sensor<Object>> findAll() {
		Session session = HibernateUtil.getSession();
		List<Sensor<Object>> list = session.createQuery("FROM "+Sensor.class.getName()).list();
		session.close();
		
		return list;
	}

	@Override
	public Sensor<Object> createUpdate(Sensor<Object> obj){
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();		
			session.saveOrUpdate(obj);
			transaction.commit();
			
		} catch (HibernateException e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return obj;
		
	}

	@Override
	public void delete(Sensor<Object> obj) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();		
			session.delete(obj);
			transaction.commit();
			
		} catch (HibernateException e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
	
	}





	

}
