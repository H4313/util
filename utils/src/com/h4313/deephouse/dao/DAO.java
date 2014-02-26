package com.h4313.deephouse.dao;



import java.util.List;

import org.hibernate.Session;

import com.h4313.deephouse.exceptions.DeepHouseTypeException;

public abstract class DAO<T> {
	

		/**
		 * Session de connection � la BD
		 */
//		public static volatile Session session = null;
		
		/**
		 * Permet de r�cup�rer un objet via son ID
		 * @param id
		 * @return
		 * @throws DeepHouseTypeException 
		 */
		public abstract T find(Object id) throws DeepHouseTypeException;
		
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
		public abstract T createUpdate(T obj) ;
		
		

		
		/**
		 * Permet la suppression d'une entr�e de la base
		 * @param obj
		 */
		public abstract void delete(T obj);
	

	
}
