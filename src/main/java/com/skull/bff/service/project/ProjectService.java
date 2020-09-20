package com.skull.bff.service.project;

import java.util.UUID;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.skull.bff.dto.project.ProjectDto;

/**
 * Interface for project service.
 * 
 * @author Carlos Feitosa
 * @since 2020-09-20
 *
 */
public interface ProjectService {

	/**
	 * Default route (list all projects).
	 * 
	 * @return list of all projects
	 */
	CollectionModel<ProjectDto> getAll();

	/**
	 * Default route (save a new project)
	 * 
	 * @param projectDto project to save
	 * 
	 * @return saved project
	 */
	EntityModel<ProjectDto> newItem(@RequestBody ProjectDto projectDto);

	/**
	 * Return project by id.
	 * 
	 * @param projectId project UUID
	 * 
	 * @return project by id
	 */
	EntityModel<ProjectDto> getById(@PathVariable(value = "id") UUID projectId); // NOPMD by skull on 8/22/20, 9:08 PM

	/**
	 * Update a project.
	 * 
	 * @param projectDto project dto
	 * @param projectId  project id
	 * 
	 * @return updated project
	 */
	EntityModel<ProjectDto> updateItem(@RequestBody ProjectDto projectDto, @PathVariable(value = "id") UUID projectId); // NOPMD
																														// by
																														// skull
																														// on
																														// 8/22/20,
																														// 9:08
																														// PM

	/**
	 * Delete a project.
	 * 
	 * @param projectId project id
	 */
	void deleteItem(@PathVariable(value = "id") UUID projectId); // NOPMD by skull on 8/22/20, 9:08 PM
}
