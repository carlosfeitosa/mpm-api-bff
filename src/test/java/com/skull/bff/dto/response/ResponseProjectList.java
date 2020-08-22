package com.skull.bff.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for project list response (embedded).
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-05
 *
 */
public class ResponseProjectList { // NOPMD by skull on 8/8/20, 6:59 PM

	/**
	 * Embbedded object on response object.
	 */
	@JsonProperty("_embedded")
	private EmbeddedProjectList embedded;

	public EmbeddedProjectList getEmbedded() {
		return embedded;
	}

	public void setEmbedded(final EmbeddedProjectList embedded) {
		this.embedded = embedded;
	}
}
