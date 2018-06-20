package edu.hm.cs.cieserver.course.importer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;

public class NineCourseAppointment {

	@JsonDeserialize(using = ICalendarDateDeserializer.class)
	private Date begin;

	@JsonDeserialize(using = ICalendarDateDeserializer.class)
	private Date end;

	private String title;

	private boolean isCanceled;

	private List<NineRoom> rooms;

	private List<NineLecturer> lecturer;

	@JsonIgnore
	private Object actions;

	public NineCourseAppointment() {
		// Default constructor for Jackson.
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(boolean cancelled) {
		isCanceled = cancelled;
	}

	public List<NineRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<NineRoom> rooms) {
		this.rooms = rooms;
	}

	public List<NineLecturer> getLecturer() {
		return lecturer;
	}

	public void setLecturer(List<NineLecturer> lecturer) {
		this.lecturer = lecturer;
	}

	public Object getActions() {
		return actions;
	}

	public void setActions(Object actions) {
		this.actions = actions;
	}
}
