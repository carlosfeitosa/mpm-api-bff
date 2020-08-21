package com.skull.bff.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for project dates.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-08
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectDatesDto {

	/**
	 * Project start date.
	 */
	private Date startDate;

	/**
	 * Project end date.
	 */
	private Date endDate;

	/**
	 * Project real start.
	 */
	private Date realStartDate;

	/**
	 * Project new end date.
	 */
	private Date newEndDate;

}
