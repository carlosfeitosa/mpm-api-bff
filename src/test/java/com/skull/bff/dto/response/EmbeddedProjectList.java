package com.skull.bff.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skull.bff.dto.project.ProjectDto;

/**
 * Class for embedded project dto list (response).
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-05
 *
 */
public class EmbeddedProjectList { // NOPMD by skull on 8/8/20, 7:00 PM

	/**
	 * Project list.
	 */
	@JsonProperty("projectDtoes")
	private List<ProjectDto> projectDtoes;

	public List<ProjectDto> getProjectDtoes() {

		return projectDtoes;
	}

	public void setProjectDtoes(final List<ProjectDto> projectDtoes) {

		this.projectDtoes = projectDtoes;
	}

}
