package edu.hm.cs.cieserver.course.importer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class NineCourse {

	private String id;

	private String name;
	private String shortName;

	private String description;

	private boolean isCoterie;
	private boolean hasHomeBias;

	private List<NineCorrelations> correlations;
	private List<NineCourseAppointment> dates;

	@JsonIgnore
	private Object actions;

	public NineCourse() {
		// Default constructor for Jackson.
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsCoterie() {
		return isCoterie;
	}

	public void setIsCoterie(boolean coterie) {
		isCoterie = coterie;
	}

	public boolean getHasHomeBias() {
		return hasHomeBias;
	}

	public void setHasHomeBias(boolean hasHomeBias) {
		this.hasHomeBias = hasHomeBias;
	}

	public List<NineCorrelations> getCorrelations() {
		return correlations;
	}

	public void setCorrelations(List<NineCorrelations> correlations) {
		this.correlations = correlations;
	}

	public List<NineCourseAppointment> getDates() {
		return dates;
	}

	public void setDates(List<NineCourseAppointment> dates) {
		this.dates = dates;
	}

	public Object getActions() {
		return actions;
	}

	public void setActions(Object actions) {
		this.actions = actions;
	}
}
