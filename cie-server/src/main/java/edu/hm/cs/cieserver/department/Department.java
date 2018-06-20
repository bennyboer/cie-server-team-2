package edu.hm.cs.cieserver.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hm.cs.cieserver.course.Course;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department {

	@Id
	private Long number;

	private String name;

	private int color;

	@JsonIgnore
	@OneToMany(mappedBy = "department")
	private Set<Course> courseSet;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Set<Course> getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(Set<Course> courseSet) {
		this.courseSet = courseSet;
	}

}
