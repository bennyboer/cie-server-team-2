package edu.hm.cs.cieserver.course.date;


import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hm.cs.cieserver.course.Course;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class CourseAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Week day in range [1, 7] where 1 is monday and 7 is sunday.
	 */
	@Min(1)
	@Max(7)
	private int weekday;

	/**
	 * Start hour in range [0, 24].
	 */
	@Min(0)
	@Max(24)
	private int startHour;

	/**
	 * Start minute in range [0, 59].
	 */
	@Min(0)
	@Max(59)
	private int startMinute;

	/**
	 * Duration of the course appointment in minutes.
	 */
	@Min(0)
	private int duration;

	@ManyToOne
	@JsonIgnore
	private Course course;

	public CourseAppointment() {
		// Default constructor for jackson.
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getWeekday() {
		return weekday;
	}

	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
