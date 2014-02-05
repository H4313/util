package com.h4313.deephouse.dao;



import java.util.List;

import org.hibernate.Session;

import com.h4313.deephouse.exceptions.DeepHouseTypeException;
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
		 * @throws DeepHouseTypeException 
		 */
		public abstract T find(Object id) throws DeepHouseTypeException;
		
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
		public abstract T createUpdate(T obj) ;
		
		

		
		/**
		 * Permet la suppression d'une entrée de la base
		 * @param obj
		 */
		public abstract void delete(T obj);
	

	
}
