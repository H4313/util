package com.h4313.deephouse.dao;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.h4313.deephouse.util.HibernateUtil;

public abstract class DAO<T> {
	

		/**
		 * Session de connection à la BD
		 */
		public Session session = HibernateUtil.getSession();
		
		/**
		 * Permet de récupérer un objet via son ID
		 * @param id
		 * @return
		 */
		public abstract T find(int id);
		
		/**
		 * Permet de récupèrer l'ensemble des objets de type T
		 * @param obj
		 */
		public abstract List<T> findAll();

		
		/**
		 * Permet de créer une entrée dans la base de données
		 * par rapport à un objet
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
		 * Permet la suppression d'une entrée de la base
		 * @param obj
		 */
		public abstract void delete(T obj);
	

	
}
