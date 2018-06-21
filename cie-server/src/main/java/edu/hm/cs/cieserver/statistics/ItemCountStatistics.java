package edu.hm.cs.cieserver.statistics;

public class ItemCountStatistics {

	private Long courses;
	private Long lecturers;
	private Long campuses;
	private Long departments;
	private Long users;

	public ItemCountStatistics(Long courses, Long lecturers, Long campuses, Long departments, Long users) {
		this.courses = courses;
		this.lecturers = lecturers;
		this.campuses = campuses;
		this.departments = departments;
		this.users = users;
	}

	public Long getCourses() {
		return courses;
	}

	public void setCourses(Long courses) {
		this.courses = courses;
	}

	public Long getLecturers() {
		return lecturers;
	}

	public void setLecturers(Long lecturers) {
		this.lecturers = lecturers;
	}

	public Long getCampuses() {
		return campuses;
	}

	public void setCampuses(Long campuses) {
		this.campuses = campuses;
	}

	public Long getDepartments() {
		return departments;
	}

	public void setDepartments(Long departments) {
		this.departments = departments;
	}

	public Long getUsers() {
		return users;
	}

	public void setUsers(Long users) {
		this.users = users;
	}
	
}
