package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.h4313.deephouse.actuator.Actuator;
import com.h4313.deephouse.exceptions.DeepHouseTypeException;
import com.h4313.deephouse.util.HibernateUtil;


public class ActuatorDAO extends DAO<Actuator<Object>> {

	public ActuatorDAO()
	{
//		if(session == null)
//		{
//			session = HibernateUtil.getSession();
//		}
	}
	
	@Override
	public Actuator<Object> find(Object id) throws DeepHouseTypeException {
		
		if (!(id instanceof Integer)) {
			throw new DeepHouseTypeException("Object is not good type, except : Integer");
		}
		
		Session session = HibernateUtil.getSession();
		Actuator<Object> actuator = (Actuator<Object>) session.get(Actuator.class,(Integer) id );
		session.close();
		
		return actuator;
	}
	
	



	@SuppressWarnings("unchecked")
	@Override
	public List<Actuator<Object>> findAll() {
		
		Session session = HibernateUtil.getSession();
		List<Actuator<Object>> list = session.createQuery("FROM "+Actuator.class.getName()).list();
		session.close();
		
		return list;
	}

	@Override
	public Actuator<Object> createUpdate(Actuator<Object> obj){
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
	public void delete(Actuator<Object> obj) {
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
