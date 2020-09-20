package com.skull.bff.service.project.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.skull.bff.client.eureka.project.ProjectClient;
import com.skull.bff.dto.project.ProjectDto;
import com.skull.bff.service.project.ProjectService;
import com.skull.bff.util.ApiUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for project.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-09-20
 *
 */
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

	/**
	 * Client for project micro service.
	 */
	@Autowired
	private ProjectClient client; // NOPMD by skull on 8/22/20, 9:10 PM

	@Override
	public CollectionModel<ProjectDto> getAll() {

		log.info("Getting all projects");
		CollectionModel<ProjectDto> result = client.getAll();

		log.info("Removing links from backend - getAll");
		result = ApiUtil.removeLinks(result);

		log.info("Converting dtos");

		return result;
	}

	@Override
	public EntityModel<ProjectDto> newItem(ProjectDto projectDto) {

		log.info("Creating new item");
		log.debug(String.format("Project name: %s", projectDto.getName()));

		EntityModel<ProjectDto> result = client.newItem(projectDto);

		log.info("Removing links from backend - newItem");
		result = ApiUtil.removeLinks(result);

		return result;
	}

	@Override
	public EntityModel<ProjectDto> getById(UUID projectId) {

		log.info("Getting project by id");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		EntityModel<ProjectDto> result = client.getById(projectId);

		log.info("Removing links from backend - getById");
		result = ApiUtil.removeLinks(result);

		return result;
	}

	@Override
	public EntityModel<ProjectDto> updateItem(ProjectDto projectDto, UUID projectId) {

		log.info("Updating project");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		EntityModel<ProjectDto> result = client.updateItem(projectDto, projectId);

		log.info("Removing links from backend - updateItem");
		result = ApiUtil.removeLinks(result);

		return result;
	}

	@Override
	public void deleteItem(UUID projectId) {

		log.info("Deleting project");

		client.deleteItem(projectId);
	}
}
