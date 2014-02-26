package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Session;

import com.h4313.deephouse.exceptions.DeepHouseTypeException;
import com.h4313.deephouse.housemodel.House;
import com.h4313.deephouse.housemodel.Room;
import com.h4313.deephouse.util.HibernateUtil;

public class HouseDAO extends DAO<House> {

	public HouseDAO()
	{
//		if(session == null)
//		{
//			session = HibernateUtil.getSession();
//		}
	}
	
	@Override
	public House find(Object id) throws DeepHouseTypeException {

		if (!(id instanceof Integer)) {
			throw new DeepHouseTypeException(this.getClass().getName()
					+ ",   Object is not good type, except : Integer");
		}
		
		Session session = HibernateUtil.getSession();
		House house = (House) session.get(House.class, (Integer) id);
		session.close();
		
		return house;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<House> findAll() {
		
		Session session = HibernateUtil.getSession();
		List<House> list = session.createQuery("FROM " + House.class.getName()).list();
		session.close();
		
		return list;
	}

	@Override
	public House createUpdate(House obj) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			for (Room r : obj.getRooms()) {
				session.saveOrUpdate(r);
			}
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
	public void delete(House house) {
		if (house != null) {
			Session session = null;
			Transaction transaction = null;
			try {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				for (Room r : house.getRooms()) {
					session.delete(r);
				}
				session.delete(house);
				transaction.commit();

			} catch (HibernateException e) {
				if(transaction != null) transaction.rollback();
				e.printStackTrace();
			} finally {
				if(session != null) session.close();
			}
		}
	}

}
