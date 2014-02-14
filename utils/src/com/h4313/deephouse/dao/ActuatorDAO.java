package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.exceptions.DeepHouseTypeException;


public class ActuatorDAO extends DAO<Actuator<Object>> {

	@Override
	public Actuator<Object> find(Object id) throws DeepHouseTypeException {
		
		if (!(id instanceof Integer)) {
			throw new DeepHouseTypeException("Object is not good type, except : Integer");
		}
		return (Actuator<Object>) session.get(Actuator.class,(Integer) id );
		
		
	}
	
	



	@SuppressWarnings("unchecked")
	@Override
	public List<Actuator<Object>> findAll() {
			return session.createQuery("FROM "+Actuator.class.getName()).list();
	}

	@Override
	public Actuator<Object> createUpdate(Actuator<Object> obj){
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
	public void delete(Actuator<Object> obj) {
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
