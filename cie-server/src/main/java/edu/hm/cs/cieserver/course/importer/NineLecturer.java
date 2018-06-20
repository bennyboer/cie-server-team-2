package edu.hm.cs.cieserver.course.importer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NineLecturer {

	private String title;
	private String firstName;
	private String lastName;

	@JsonIgnore
	private Object actions;

	public NineLecturer() {
		// Default constructor for Jackson.
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Object getActions() {
		return actions;
	}

	public void setActions(Object actions) {
		this.actions = actions;
	}
}
