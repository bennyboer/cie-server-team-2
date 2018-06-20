package edu.hm.cs.cieserver.course.importer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NineCorrelations {

	private String organiser;
	private String curriculum;
	@JsonIgnore
	private Object actions;

	public NineCorrelations() {
		// Default constructor for Jackson.
	}

	public String getOrganiser() {
		return organiser;
	}

	public void setOrganiser(String organiser) {
		this.organiser = organiser;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}

	public Object getActions() {
		return actions;
	}

	public void setActions(Object actions) {
		this.actions = actions;
	}
}
