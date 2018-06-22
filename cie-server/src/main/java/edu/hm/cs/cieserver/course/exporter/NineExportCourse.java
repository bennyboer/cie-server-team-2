package edu.hm.cs.cieserver.course.exporter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hm.cs.cieserver.campus.Campus;
import edu.hm.cs.cieserver.course.Course;
import edu.hm.cs.cieserver.course.CourseLevel;
import edu.hm.cs.cieserver.course.CourseStatus;
import edu.hm.cs.cieserver.course.date.CourseAppointment;
import edu.hm.cs.cieserver.department.Department;
import edu.hm.cs.cieserver.lecturer.Lecturer;
import edu.hm.cs.cieserver.user.User;

import java.util.Set;

/**
 * Special class for export to NINE.
 * The maintainer of NINE (nine.wi.hm.edu) wanted some changes for him to
 * import the courses from the cie server more easier.
 */
public class NineExportCourse {

	@JsonIgnore
	private Course course;

	public NineExportCourse(Course course) {
		this.course = course;
	}

	public String getId() {
		return "CIE" + String.format("%07d", course.getId());
	}

	public String getName() {
		return course.getName();
	}

	public String getDescription() {
		return course.getDescription();
	}

	public int getAvailableSlots() {
		return course.getAvailableSlots();
	}

	public double getEcts() {
		return course.getEcts();
	}

	public double getUsCredits() {
		return course.getUsCredits();
	}

	public double getSemesterWeekHours() {
		return course.getSemesterWeekHours();
	}

	public CourseStatus getCourseStatus() {
		return course.getCourseStatus();
	}

	public String getLecturer() {
		if (course.getLecturer() != null) {
			return course.getLecturer().getName();
		} else {
			return "";
		}
	}

	public String getDepartment() {
		if (course.getDepartment() != null) {
			return String.format("FK %02d", course.getDepartment().getNumber());
		} else {
			return "";
		}
	}

	public Campus getLocation() {
		return course.getLocation();
	}

	public Set<CourseAppointment> getCourseAppointments() {
		return course.getCourseAppointments();
	}

	public String getLevel() {
		if (course.getLevel() == null) {
			return CourseLevel.ANY.name();
		}

		return course.getLevel().name();
	}

}
