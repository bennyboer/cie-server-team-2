package edu.hm.cs.cieserver.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hm.cs.cieserver.campus.Campus;
import edu.hm.cs.cieserver.department.Department;
import edu.hm.cs.cieserver.lecturer.Lecturer;
import edu.hm.cs.cieserver.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Course representation.
 */
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Name of the course.
	 */
	private String name;

	private String description;

	/**
	 * Where the course is.
	 */
	private String room;

	/**
	 * How many slots are available.
	 */
	private int availableSlots;

	/**
	 * ECTS for this course.
	 */
	private double ects;

	/**
	 * US Credits for this course.
	 */
	private double usCredits;

	/**
	 * Semester week hours of the course.
	 */
	private double semesterWeekHours;

	/**
	 * When the course is.
	 */
	private Date[] dates;

	/**
	 * Duration in minutes.
	 */
	private int duration;

	/**
	 * Status of the course.
	 */
	@Enumerated(EnumType.STRING)
	private CourseStatus courseStatus;

	/**
	 * Lecturer of the course.
	 */
	@ManyToOne
	private Lecturer lecturer;

	/**
	 * Of which department the course is.
	 */
	@ManyToOne
	private Department department;

	/**
	 * Location (Campus) the course is at.
	 */
	@ManyToOne
	private Campus location;

	/**
	 * Set of users who selected this course.
	 */
	@JsonIgnore
	@ManyToMany(mappedBy = "selectedCourses")
	private Set<User> selectedBy;

	/**
	 * Set of users who favorited this course.
	 */
	@JsonIgnore
	@ManyToMany(mappedBy = "favorizedCourses")
	private Set<User> favorizedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(int availableSlots) {
		this.availableSlots = availableSlots;
	}

	public double getEcts() {
		return ects;
	}

	public void setEcts(double ects) {
		this.ects = ects;
	}

	public double getUsCredits() {
		return usCredits;
	}

	public void setUsCredits(double usCredits) {
		this.usCredits = usCredits;
	}

	public double getSemesterWeekHours() {
		return semesterWeekHours;
	}

	public void setSemesterWeekHours(double semesterWeekHours) {
		this.semesterWeekHours = semesterWeekHours;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Campus getLocation() {
		return location;
	}

	public void setLocation(Campus location) {
		this.location = location;
	}

	public Set<User> getSelectedBy() {
		return selectedBy;
	}

	public void setSelectedBy(Set<User> selectedBy) {
		this.selectedBy = selectedBy;
	}

	public Set<User> getFavorizedBy() {
		return favorizedBy;
	}

	public void setFavorizedBy(Set<User> favorizedBy) {
		this.favorizedBy = this.favorizedBy;
	}
}