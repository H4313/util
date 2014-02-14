package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.h4313.deephouse.exceptions.DeepHouseTypeException;
import com.h4313.deephouse.housemodel.Room;


public class RoomDAO extends DAO<Room> {

	@Override
	public Room find(Object id) throws DeepHouseTypeException {
		
		if (!(id instanceof Integer)) {
			throw new DeepHouseTypeException("Object is not good type, except : Integer");
		}
		return (Room) session.get(Room.class,(Integer) id );
		
		
	}
	
	



	@SuppressWarnings("unchecked")
	@Override
	public List<Room> findAll() {
			return session.createQuery("FROM "+Room.class.getName()).list();
	}

	@Override
	public Room createUpdate(Room obj){
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
	public void delete(Room obj) {
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
