package com.skull.bff.client.eureka;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.skull.bff.client.eureka.project.ProjectClient;
import com.skull.bff.dto.project.ProjectClientInformationDto;
import com.skull.bff.dto.project.ProjectDatesDto;
import com.skull.bff.dto.project.ProjectDto;

import feign.FeignException.NotFound;

@SpringBootTest
class ProjectClientTest {

	private static final String TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION = "Project not available for id";
	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";

	@Autowired
	private ProjectClient client;

	@Test
	@DisplayName("Test if client can get all elements")
	void testGetAll() {

		CollectionModel<ProjectDto> projectList = client.getAll();

		assertThat(projectList).isNotEmpty();
	}

	@Test
	@DisplayName("Test if client can get item by id")
	void testIfCanGetProjectById() {

		CollectionModel<ProjectDto> projectList = client.getAll();

		for (ProjectDto project : projectList) {

			EntityModel<ProjectDto> foundProject = client.getById(project.getId());

			assertThat(foundProject.getContent().getId()).isEqualTo(project.getId());
		}
	}

	@Test
	@DisplayName("Test if client throws exception trying to get item by invalid id")
	void testIfThrowsExceptionTryingGetProjectByInvalidId() {

		UUID invalidProjectId = UUID.randomUUID();

		Exception exception = assertThrows(NotFound.class, () -> {

			client.getById(invalidProjectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);
	}

	@Test
	@DisplayName("Test if client can save a new project")
	void testIfCanCreateAProject() {

		ProjectDto projectDto = new ProjectDto();
		ProjectDatesDto datesDto = new ProjectDatesDto();
		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);

		datesDto.setStartDate(new Date());
		datesDto.setEndDate(new Date());

		projectDto.setDates(datesDto);

		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setClientInformation(clientInformation);

		EntityModel<ProjectDto> createdProjectDto = client.newItem(projectDto);

		assertThat(createdProjectDto.getContent().getId()).isNotNull();
	}

	@Test
	@DisplayName("Test if client can update a project")
	void testIfCanUpdateAProject() {

		ProjectDto projectDto = new ProjectDto();
		ProjectDatesDto datesDto = new ProjectDatesDto();
		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);

		datesDto.setStartDate(new Date());
		datesDto.setEndDate(new Date());

		projectDto.setDates(datesDto);

		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setClientInformation(clientInformation);

		EntityModel<ProjectDto> createdProjectDto = client.newItem(projectDto);

		assertThat(createdProjectDto.getContent().getId()).isNotNull();

		UUID newClientId = UUID.randomUUID();

		createdProjectDto.getContent().getClientInformation().setClientId(newClientId);

		EntityModel<ProjectDto> updatedProject = client.updateItem(createdProjectDto.getContent(),
				createdProjectDto.getContent().getId());

		assertThat(updatedProject.getContent().getClientInformation().getClientId()).isEqualTo(newClientId);
	}

	@Test
	@DisplayName("Test if client throws exception trying to update a project id")
	void testIfUpdateAProjectIdThrowsException() {

		ProjectDto projectDto = new ProjectDto();
		ProjectDatesDto dates = new ProjectDatesDto();
		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);

		dates.setStartDate(new Date());
		dates.setEndDate(new Date());

		projectDto.setDates(dates);

		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setClientInformation(clientInformation);

		EntityModel<ProjectDto> createdProjectDto = client.newItem(projectDto);

		assertThat(createdProjectDto.getContent().getId()).isNotNull();

		UUID newProjectId = UUID.randomUUID();

		createdProjectDto.getContent().setId(newProjectId);

		ProjectDto projectForUpdate = createdProjectDto.getContent();

		Exception exception = assertThrows(NotFound.class, () -> {

			client.updateItem(projectForUpdate, newProjectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);
	}

	@Test
	@DisplayName("Test if client can delete a project")
	void testIfCanDeleteAProject() {

		ProjectDto projectDto = new ProjectDto();
		ProjectDatesDto dates = new ProjectDatesDto();
		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);

		dates.setStartDate(new Date());
		dates.setEndDate(new Date());

		projectDto.setDates(dates);

		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setClientInformation(clientInformation);

		EntityModel<ProjectDto> createdProjectDto = client.newItem(projectDto);

		assertThat(createdProjectDto.getContent().getId()).isNotNull();

		UUID projectId = createdProjectDto.getContent().getId();

		client.deleteItem(projectId);

		Exception exception = assertThrows(NotFound.class, () -> {

			client.getById(projectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);
	}

	@Test
	@DisplayName("Test if client can throw an exception trying to delete an invalid project")
	void testIfThrowExceptionTryingToDeleteAnInvalidProject() {

		UUID projectId = UUID.randomUUID();

		Exception exception = assertThrows(NotFound.class, () -> {

			client.getById(projectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);
	}

}
