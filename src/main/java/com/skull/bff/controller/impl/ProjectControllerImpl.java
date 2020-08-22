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
@RequestMapping("${service.request.mapping}/${service.project.request.mapping}")
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
	public EntityModel<ProjectDto> newItem(final ProjectDto projectDto) {

		log.info("Creating new item");
		log.debug(String.format("Project name: %s", projectDto.getName()));

		return client.newItem(projectDto);
	}

	@Override
	public EntityModel<ProjectDto> getById(final UUID projectId) {

		log.info("Getting project by id");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		return client.getById(projectId);
	}

	@Override
	public EntityModel<ProjectDto> updateItem(final ProjectDto projectDto, final UUID projectId) {

		log.info("Updating project");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		return client.updateItem(projectDto, projectId);
	}

	@Override
	public void deleteItem(final UUID projectId) {

		log.info("Deleting project");

		client.deleteItem(projectId);
	}

}
