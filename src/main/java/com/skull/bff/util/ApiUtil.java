package com.skull.bff.util;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import lombok.extern.slf4j.Slf4j;

/**
 * API utilitary class.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-09-20
 *
 */
@Slf4j
public class ApiUtil {

	/**
	 * Remove links from entity list.
	 * 
	 * @param <T>             dto entity class
	 * @param entityList      dto list
	 * @param cascadeEntities boolean if true remove links from entities within
	 *                        collection
	 * 
	 * @return dto list without links
	 */
	public static <T> CollectionModel<T> removeLinks(CollectionModel<T> entityList) {

		log.info("Removing links from collection model");
		entityList.removeLinks();

		return entityList;
	}

	/**
	 * Remove links from entity.
	 * 
	 * @param <T>    dto entity class
	 * @param entity dto entity
	 * 
	 * @return dto entity without links
	 */
	public static <T> EntityModel<T> removeLinks(EntityModel<T> entity) {

		log.info("Removing link from entity model");
		entity.removeLinks();

		return entity;
	}
}
