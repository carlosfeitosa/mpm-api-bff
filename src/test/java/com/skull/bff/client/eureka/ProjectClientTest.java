package com.skull.bff.client.eureka;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;

import com.skull.bff.dto.ProjectDto;

@SpringBootTest
class ProjectClientTest {

	@Autowired
	private ProjectClient client;

	@Test
	@DisplayName("Test if client can get all elements")
	void testGetAll() {

		CollectionModel<ProjectDto> projectList = client.getAll();

		assertThat(projectList).isNotEmpty();
	}

	@Test
	void testNewItem() {
		fail("Not yet implemented");
	}

	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateItem() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteItem() {
		fail("Not yet implemented");
	}

}
