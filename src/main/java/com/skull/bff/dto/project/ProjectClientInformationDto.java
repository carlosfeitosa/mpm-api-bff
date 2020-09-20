package com.skull.bff.dto.project;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for project client information.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-08
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectClientInformationDto {

	/**
	 * Project client's id.
	 */
	private UUID clientId;

	/**
	 * Project client's name.
	 */
	private String clientName;

	/**
	 * Project client's project code.
	 */
	private String clientProjectCode;

}
