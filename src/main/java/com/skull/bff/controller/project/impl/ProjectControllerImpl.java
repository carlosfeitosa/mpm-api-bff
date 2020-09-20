package com.skull.bff.controller.project.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skull.bff.controller.project.ProjectController;
import com.skull.bff.dto.project.ProjectDto;
import com.skull.bff.service.project.ProjectService;

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
public class ProjectControllerImpl implements ProjectController { // NOPMD by skull on 8/22/20, 9:10 PM

	/**
	 * Service for project.
	 */
	@Autowired
	private ProjectService service;

	@Override
	@GetMapping
	public CollectionModel<ProjectDto> getAll() {

		log.info("API: Getting all projects");

		return service.getAll();
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<ProjectDto> newItem(final ProjectDto projectDto) {

		log.info("API: Creating new item");

		return service.newItem(projectDto);
	}

	@Override
	@GetMapping("/{id}")
	public EntityModel<ProjectDto> getById(final UUID projectId) {

		log.info("API: Getting project by id");

		return service.getById(projectId);
	}

	@Override
	@PutMapping("/{id}")
	public EntityModel<ProjectDto> updateItem(final ProjectDto projectDto, final UUID projectId) {

		log.info("API: Updating project");

		return service.updateItem(projectDto, projectId);
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteItem(final UUID projectId) {

		log.info("API: Deleting project");

		service.deleteItem(projectId);
	}

}
