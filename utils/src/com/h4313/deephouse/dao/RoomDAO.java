package com.h4313.deephouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.h4313.deephouse.housemodel.Room;

public class RoomDAO extends DAO<Room> {

	@Override
	public Room find(int id) {
		
		return (Room) session.get(Room.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> findAll() {
			return session.createQuery("FROM "+Room.class.getName()).list();
	}

	@Override
	public Room createUpdate(Room obj){
		//super(obj);
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
			session.close();
		}
	
	}

	

}
