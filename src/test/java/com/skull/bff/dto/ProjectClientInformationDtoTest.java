package com.skull.bff.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProjectClientInformationDtoTest {

	private static final String TEST_CLIENT_PROJECT_CODE = "AAA-00001234";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";

	@Test
	@DisplayName("Test if can perform equals and hashcode")
	void testIfCanPerformEqualsAndHashCode() {

		UUID clientId = UUID.randomUUID();

		ProjectClientInformationDto clientInformation1 = new ProjectClientInformationDto();
		clientInformation1.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		clientInformation1.setClientId(clientId);
		clientInformation1.setClientName(TEST_PROJECT_CLIENT_NAME);

		ProjectClientInformationDto clientInformation2 = new ProjectClientInformationDto();
		clientInformation2.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		clientInformation2.setClientId(clientId);
		clientInformation2.setClientName(TEST_PROJECT_CLIENT_NAME);

		assertThat(clientInformation1).isEqualTo(clientInformation2).hasSameHashCodeAs(clientInformation2);
	}

}
