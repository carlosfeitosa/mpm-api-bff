package com.skull.bff.controller.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skull.bff.client.eureka.ProjectClient;
import com.skull.bff.controller.ProjectController;
import com.skull.bff.dto.ProjectDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller for project API.
 * 
 * @author Carlo Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-19
 *
 */
@RestController
@RequestMapping("${service.request.mapping}")
@Slf4j
public class ProjectControllerImpl implements ProjectController {

	/**
	 * Client for project micro service.
	 */
	@Autowired
	private ProjectClient client;

	@Override
	@GetMapping
	public CollectionModel<ProjectDto> getAll() {

		log.info("Getting all projects");

		return client.getAll();
	}

	@Override
	public EntityModel<ProjectDto> newItem(ProjectDto projectDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityModel<ProjectDto> getById(UUID projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityModel<ProjectDto> updateItem(ProjectDto projectDto, UUID projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteItem(UUID projectId) {
		// TODO Auto-generated method stub

	}

}
