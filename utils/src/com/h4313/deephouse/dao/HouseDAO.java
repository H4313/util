package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.h4313.deephouse.exceptions.DeepHouseTypeException;
import com.h4313.deephouse.housemodel.House;



public class HouseDAO extends DAO<House> {

	@Override
	public House find(Object id) throws DeepHouseTypeException  {

		if (!(id instanceof Integer)) {
			throw new DeepHouseTypeException(this.getClass().getName()+",   Object is not good type, except : Integer");
		}
		return (House) session.get(House.class,(Integer) id);
	}
	
	



	@SuppressWarnings("unchecked")
	@Override
	public List<House> findAll() {
			return session.createQuery("FROM "+House.class.getName()).list();
	}

	@Override
	public House createUpdate(House obj){
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
	public void delete(House obj) {
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
