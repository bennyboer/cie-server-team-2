package edu.hm.cs.cieserver.course.importer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NineRoom {

	private String number;
	private String building;
	private String campus;

	@JsonIgnore
	private Object actions;

	public NineRoom() {
		// Default constructor for Jackson.
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public Object getActions() {
		return actions;
	}

	public void setActions(Object actions) {
		this.actions = actions;
	}
}
