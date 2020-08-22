package com.skull.bff.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skull.bff.enums.ProjectHealthEnum;
import com.skull.bff.enums.ProjectStatusEnum;
import com.skull.bff.enums.ProjectTypeEnum;

class ProjectDtoTest {

	private static final String TEST_PROJECT_CODE = "00001234";
	private static final String TEST_CLIENT_PROJECT_CODE = "AAA-00001234";
	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";
	private static final Long TEST_PROJECT_TOTAL_CONTRACTED_HOURS = 100000L;
	private static final String TEST_PROJECT_ATTENTION_POINTS = "No attention points for now";
	private static final String TEST_PROJECT_ACTION_PLAN = "Let's do it!";
	private static final ProjectTypeEnum TEST_PROJECT_TYPE = ProjectTypeEnum.INTERNAL_PROJECT;
	private static final ProjectStatusEnum TEST_PROJECT_STATUS = ProjectStatusEnum.ON_HOLD;
	private static final ProjectHealthEnum TEST_PROJECT_HEALTH = ProjectHealthEnum.ALERT;

	@Test
	@DisplayName("Test if can perform equals and hashcode")
	void testIfCanPerformEqualsAndHashCode() {

		ProjectDto project1 = new ProjectDto();
		ProjectDto project2 = new ProjectDto();

		UUID projectId = UUID.randomUUID();
		UUID clientId = UUID.randomUUID();
		Date startDate = new Date();
		Date endDate = new Date();
		Date realStartDate = new Date();
		Date newEndDate = new Date();

		ProjectDatesDto dates = new ProjectDatesDto();
		dates.setStartDate(startDate);
		dates.setEndDate(endDate);
		dates.setRealStartDate(realStartDate);
		dates.setNewEndDate(newEndDate);

		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();
		clientInformation.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		clientInformation.setClientId(clientId);
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		ProjectClientInformationDto clientInformation2 = new ProjectClientInformationDto();
		clientInformation2.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		clientInformation2.setClientId(clientId);
		clientInformation2.setClientName(TEST_PROJECT_CLIENT_NAME);

		project1.setCode(TEST_PROJECT_CODE);
		project1.setId(projectId);
		project1.setName(TEST_PROJECT_NAME);
		project1.setDescription(TEST_PROJECT_DESCRIPTION);
		project1.setType(TEST_PROJECT_TYPE);
		project1.setStatus(TEST_PROJECT_STATUS);
		project1.setHealth(TEST_PROJECT_HEALTH);
		project1.setDates(dates);
		project1.setClientInformation(clientInformation);
		project1.setTotalHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		project1.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		project1.setActionPlan(TEST_PROJECT_ACTION_PLAN);

		project2.setCode(TEST_PROJECT_CODE);
		project2.setId(projectId);
		project2.setName(TEST_PROJECT_NAME);
		project2.setDescription(TEST_PROJECT_DESCRIPTION);
		project2.setType(TEST_PROJECT_TYPE);
		project2.setStatus(TEST_PROJECT_STATUS);
		project2.setHealth(TEST_PROJECT_HEALTH);
		project2.setDates(dates);
		project2.setClientInformation(clientInformation2);
		project2.setTotalHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		project2.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		project2.setActionPlan(TEST_PROJECT_ACTION_PLAN);

		assertThat(project1).isEqualTo(project2).hasSameHashCodeAs(project2);

		project2.getClientInformation().setClientId(UUID.randomUUID());

		assertThat(project1.hashCode()).isNotEqualTo(project2.hashCode());
	}

	@Test
	@DisplayName("Test if can perform toString()")
	void testIfCanPerformToString() {

		ProjectDto projectDto = new ProjectDto();

		UUID projectId = UUID.randomUUID();
		UUID clientId = UUID.randomUUID();
		Date startDate = new Date();
		Date endDate = new Date();
		Date realStartDate = new Date();
		Date newEndDate = new Date();

		ProjectDatesDto dates = new ProjectDatesDto();
		dates.setStartDate(startDate);
		dates.setEndDate(endDate);
		dates.setRealStartDate(realStartDate);
		dates.setNewEndDate(newEndDate);

		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();
		clientInformation.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		clientInformation.setClientId(clientId);
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setCode(TEST_PROJECT_CODE);
		projectDto.setId(projectId);
		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);
		projectDto.setType(TEST_PROJECT_TYPE);
		projectDto.setStatus(TEST_PROJECT_STATUS);
		projectDto.setHealth(TEST_PROJECT_HEALTH);
		projectDto.setDates(dates);
		projectDto.setClientInformation(clientInformation);
		projectDto.setTotalHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		projectDto.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		projectDto.setActionPlan(TEST_PROJECT_ACTION_PLAN);

		String projectToString = projectDto.toString();

		assertThat(projectToString).contains(TEST_PROJECT_CODE);
		assertThat(projectToString).contains(projectId.toString());
		assertThat(projectToString).contains(TEST_PROJECT_NAME);
		assertThat(projectToString).contains(TEST_CLIENT_PROJECT_CODE);
		assertThat(projectToString).contains(clientId.toString());
		assertThat(projectToString).contains(TEST_PROJECT_DESCRIPTION);
		assertThat(projectToString).contains(TEST_PROJECT_CLIENT_NAME);
		assertThat(projectToString).contains(startDate.toString());
		assertThat(projectToString).contains(endDate.toString());
		assertThat(projectToString).contains(realStartDate.toString());
		assertThat(projectToString).contains(newEndDate.toString());
		assertThat(projectToString).contains(String.valueOf(TEST_PROJECT_TOTAL_CONTRACTED_HOURS));
		assertThat(projectToString).contains(TEST_PROJECT_ATTENTION_POINTS);
		assertThat(projectToString).contains(TEST_PROJECT_ACTION_PLAN);
		assertThat(projectToString).contains(TEST_PROJECT_TYPE.toString());
		assertThat(projectToString).contains(TEST_PROJECT_STATUS.toString());
		assertThat(projectToString).contains(TEST_PROJECT_HEALTH.toString());
	}
}
