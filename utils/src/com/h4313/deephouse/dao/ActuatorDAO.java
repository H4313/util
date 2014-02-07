package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.h4313.deephouse.exceptions.*;
import com.h4313.deephouse.actuator.*;


public class ActuatorDAO extends DAO<Actuator> {

	@Override
	public Actuator find(Object id) throws DeepHouseTypeException {
		
		if (!(id instanceof Integer)) {
			throw new DeepHouseTypeException("Object is not good type, except : Integer");
		}
		return (Actuator) session.get(Actuator.class,(Integer) id );
		
		
	}
	
	



	@SuppressWarnings("unchecked")
	@Override
	public List<Actuator> findAll() {
			return session.createQuery("FROM "+Actuator.class.getName()).list();
	}

	@Override
	public Actuator createUpdate(Actuator obj){
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
	public void delete(Actuator obj) {
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
