package com.skull.bff.client.eureka;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skull.bff.dto.ProjectDto;

/**
 * Project eureka client.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt#gmail.com)
 * @since 2020-08-17
 *
 */
@FeignClient("mpm-ms-project")
@RequestMapping(value = "${eureka.client.service.project.request.mapping}") // NOPMD by skull on 8/22/20, 9:07 PM
public interface ProjectClient {

	/**
	 * Default route (list all projects).
	 * 
	 * @return list of all projects
	 */
	@GetMapping
	CollectionModel<ProjectDto> getAll();

	/**
	 * Default route (save a new project)
	 * 
	 * @param projectDto project to save
	 * 
	 * @return saved project
	 */
	@PostMapping
	EntityModel<ProjectDto> newItem(@RequestBody ProjectDto projectDto);

	/**
	 * Return project by id.
	 * 
	 * @param projectId project UUID
	 * 
	 * @return project by id
	 */
	@GetMapping("/{id}")
	EntityModel<ProjectDto> getById(@PathVariable(value = "id") UUID projectId); // NOPMD by skull on 8/22/20, 9:07 PM

	/**
	 * Update a project.
	 * 
	 * @param projectDto project dto
	 * @param projectId  project id
	 * 
	 * @return updated project
	 */
	@PutMapping("/{id}")
	EntityModel<ProjectDto> updateItem(@RequestBody ProjectDto projectDto, @PathVariable(value = "id") UUID projectId); // NOPMD
																														// by
																														// skull
																														// on
																														// 8/22/20,
																														// 9:07
																														// PM

	/**
	 * Delete a project.
	 * 
	 * @param projectId project id
	 */
	@DeleteMapping("/{id}")
	void deleteItem(@PathVariable(value = "id") UUID projectId); // NOPMD by skull on 8/22/20, 9:07 PM
}
