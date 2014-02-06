package com.h4313.deephouse.dao;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.h4313.deephouse.util.HibernateUtil;

public abstract class DAO<T> {
	

		/**
		 * Session de connection � la BD
		 */
		public Session session = HibernateUtil.getSession();
		
		/**
		 * Permet de r�cup�rer un objet via son ID
		 * @param id
		 * @return
		 */
		public abstract T find(int id);
		
		/**
		 * Permet de r�cup�rer l'ensemble des objets de type T
		 * @param obj
		 */
		public abstract List<T> findAll();

		
		/**
		 * Permet de cr�er une entr�e dans la base de donn�es
		 * par rapport � un objet
		 * @param obj
		 */
		public  T createUpdate(T obj) {
			Transaction transaction = null;
			try {
				
				transaction = session.beginTransaction();		
				session.saveOrUpdate(obj);
				transaction.commit();
			
			} catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			return obj;
		}
		

		
		/**
		 * Permet la suppression d'une entr�e de la base
		 * @param obj
		 */
		public abstract void delete(T obj);
	

	
}
