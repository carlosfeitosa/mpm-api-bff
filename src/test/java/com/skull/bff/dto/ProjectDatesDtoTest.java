package com.skull.bff.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProjectDatesDtoTest {

	@Test
	@DisplayName("Test if can perform equals and hashcode")
	void testIfCanPerformEqualsAndHashCode() {

		Date startDate = new Date();
		Date endDate = new Date();
		Date realStartDate = new Date();
		Date newEndDate = new Date();

		ProjectDatesDto dates1 = new ProjectDatesDto();
		dates1.setStartDate(startDate);
		dates1.setEndDate(endDate);
		dates1.setRealStartDate(realStartDate);
		dates1.setNewEndDate(newEndDate);

		ProjectDatesDto dates2 = new ProjectDatesDto();
		dates2.setStartDate(startDate);
		dates2.setEndDate(endDate);
		dates2.setRealStartDate(realStartDate);
		dates2.setNewEndDate(newEndDate);

		assertThat(dates1).isEqualTo(dates2).hasSameHashCodeAs(dates2);
	}

}
