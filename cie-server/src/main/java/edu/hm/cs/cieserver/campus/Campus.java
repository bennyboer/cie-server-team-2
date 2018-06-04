package edu.hm.cs.cieserver.campus;

import edu.hm.cs.cieserver.course.Course;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Campus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "location")
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
}
