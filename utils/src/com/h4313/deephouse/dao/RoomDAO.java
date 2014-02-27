package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.h4313.deephouse.exceptions.DeepHouseTypeException;
import com.h4313.deephouse.housemodel.Room;
import com.h4313.deephouse.util.HibernateUtil;


public class RoomDAO extends DAO<Room> {

	public RoomDAO()
	{
//		if(session == null)
//		{
//			session = HibernateUtil.getSession();
//		}
	}
	
	@Override
	public Room find(Object id) throws DeepHouseTypeException {
		
		if (!(id instanceof Integer)) {
			throw new DeepHouseTypeException("Object is not good type, except : Integer");
		}
		
		Session session = HibernateUtil.getSession();
		Room room = (Room) session.get(Room.class,(Integer) id );
		session.close();
		
		return room;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> findAll() {
		
		Session session = HibernateUtil.getSession();
		List<Room> list = session.createQuery("FROM "+Room.class.getName()).list();
		session.close();
		
		return list;
	}

	@Override
	public Room createUpdate(Room obj){
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
	public void delete(Room obj) {
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
