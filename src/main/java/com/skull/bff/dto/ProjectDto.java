package com.skull.bff.dto;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.skull.bff.enums.ProjectHealthEnum;
import com.skull.bff.enums.ProjectStatusEnum;
import com.skull.bff.enums.ProjectTypeEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for project model.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-07-05
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectDto extends RepresentationModel<ProjectDto> {

	/**
	 * Project identifier.
	 */
	private UUID id; // NOPMD by skull on 8/8/20, 7:18 PM

	/**
	 * Project code.
	 */
	private String code;

	/**
	 * Project name.
	 */
	private String name;

	/**
	 * Project's description.
	 */
	private String description;

	/**
	 * Project's type.
	 */
	private ProjectTypeEnum type;

	/**
	 * Project's status.
	 */
	private ProjectStatusEnum status;

	/**
	 * Project's health.
	 */
	private ProjectHealthEnum health;

	/**
	 * Project's dates.
	 */
	private ProjectDatesDto dates;

	/**
	 * Project's client information.
	 */
	private ProjectClientInformationDto clientInformation;

	/**
	 * Project's total contracted hours.
	 */
	private Long totalHours;

	/**
	 * Project's attention points.
	 */
	private String attentionPoints;

	/**
	 * Project's action plan.
	 */
	private String actionPlan;

}
