package edu.hm.cs.cieserver.lecturer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hm.cs.cieserver.course.Course;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Lecturer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "lecturer")
	private Set<Course> courseSet;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Course> getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(Set<Course> courseSet) {
		this.courseSet = courseSet;
	}
}
