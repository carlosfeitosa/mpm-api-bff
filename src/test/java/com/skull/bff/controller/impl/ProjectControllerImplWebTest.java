package com.skull.bff.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skull.bff.dto.project.ProjectClientInformationDto;
import com.skull.bff.dto.project.ProjectDatesDto;
import com.skull.bff.dto.project.ProjectDto;
import com.skull.bff.dto.response.ResponseProjectList;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProjectControllerImplWebTest {

	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";

	@Value("${service.request.mapping}/${service.project.request.mapping}")
	private String requestMapping;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@LocalServerPort
	private int port;

	@Autowired
	private ObjectMapper om;

	@Autowired
	private TestRestTemplate restTemplate;

	private String endpoint;

	@BeforeEach
	void setupTest() {
		endpoint = String.format("http://localhost:%d%s/%s", port, contextPath, requestMapping);
	}

	@Test
	@DisplayName("Test if can get 200 response for default endpoint")
	void greetingShouldReturnDefaultMessage() throws Exception {

		ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Test if service can get all elements")
	void testIfCanGetAll() throws JsonProcessingException, JSONException, RestClientException, URISyntaxException {

		ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseProjectList recoveredProjects = restTemplate.exchange(new URI(endpoint), HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseProjectList>() {
				}).getBody();

		assertThat(recoveredProjects.getEmbedded().getProjectDtoes()).isNotEmpty();
	}

	@Test
	@DisplayName("Test if service can get item by id")
	void testIfCanGetById() throws JsonProcessingException, JSONException, RestClientException, URISyntaxException {

		ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ResponseProjectList recoveredProjects = restTemplate.exchange(new URI(endpoint), HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseProjectList>() {
				}).getBody();

		assertThat(recoveredProjects.getEmbedded().getProjectDtoes()).isNotEmpty();

		ProjectDto expected = recoveredProjects.getEmbedded().getProjectDtoes().get(0);

		response = restTemplate.getForEntity(String.format("%s/%s", endpoint, expected.getId().toString()),
				String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ProjectDto recoveredProject = om.readValue(response.getBody(), ProjectDto.class);

		assertEquals(expected, recoveredProject);
	}

	@Test
	@DisplayName("Test if service throws not found (404) trying to get item by invalid id")
	void testIfGetThrowsNotFound404TryingGetProjectByInvalidId() {
		ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s/%s", endpoint, UUID.randomUUID()),
				String.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	@DisplayName("Test if service can save a new project")
	void testIfCanCreateAProject() throws JsonMappingException, JsonProcessingException {

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

		ResponseEntity<String> response = this.restTemplate.postForEntity(endpoint, new HttpEntity<>(projectDto),
				String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		ProjectDto savedProject = om.readValue(response.getBody(), ProjectDto.class);

		assertThat(savedProject.getId()).isNotNull();
	}

	@Test
	@DisplayName("Test if service can update a project")
	void testIfCanUpdateAProject() throws JsonMappingException, JsonProcessingException {

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

		ResponseEntity<String> response = this.restTemplate.postForEntity(endpoint, new HttpEntity<>(projectDto),
				String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		ProjectDto savedProject = om.readValue(response.getBody(), ProjectDto.class);

		assertThat(savedProject.getId()).isNotNull();

		UUID newClientId = UUID.randomUUID();

		savedProject.getClientInformation().setClientId(newClientId);

		response = this.restTemplate.exchange(String.format("%s/%s", endpoint, savedProject.getId()), HttpMethod.PUT,
				new HttpEntity<>(savedProject), String.class);

		ProjectDto updatedProject = om.readValue(response.getBody(), ProjectDto.class);

		assertThat(updatedProject.getClientInformation().getClientId()).isEqualTo(newClientId);
	}

	@Test
	@DisplayName("Test if service throws not found (404) trying to update a project id")
	void testIfUpdateAProjectIdThrowsNotFound404Exception() throws JsonMappingException, JsonProcessingException {

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

		ResponseEntity<String> response = this.restTemplate.postForEntity(endpoint, new HttpEntity<>(projectDto),
				String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		ProjectDto savedProject = om.readValue(response.getBody(), ProjectDto.class);

		assertThat(savedProject.getId()).isNotNull();

		UUID newId = UUID.randomUUID();

		savedProject.setId(newId);

		response = this.restTemplate.exchange(String.format("%s/%s", endpoint, savedProject.getId()), HttpMethod.PUT,
				new HttpEntity<>(savedProject), String.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	@DisplayName("Test if service can delete a project")
	void testIfCanDeleteAProject() throws JsonMappingException, JsonProcessingException {

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

		ResponseEntity<String> response = this.restTemplate.postForEntity(endpoint, new HttpEntity<>(projectDto),
				String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		ProjectDto savedProject = om.readValue(response.getBody(), ProjectDto.class);

		response = this.restTemplate.exchange(String.format("%s/%s", endpoint, savedProject.getId()), HttpMethod.DELETE,
				new HttpEntity<>(savedProject), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Test if service can throws not found (404) trying to delete an invalid project")
	void testIfThrowNotFound404TryingToDeleteAnInvalidProject() {
		
		ResponseEntity<String> response = this.restTemplate.exchange(
				String.format("%s/%s", endpoint, UUID.randomUUID()), HttpMethod.DELETE,
				new HttpEntity<>(new ProjectDto()), String.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}
