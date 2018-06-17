package edu.hm.cs.cieserver.campus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hm.cs.cieserver.course.Course;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Campus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Name of the campus (Address).
	 */
	private String name;

	/**
	 * Geographical location of the campus.
	 */
	private Location location;

	/**
	 * Image of the campus
	 */
	@Lob
	@JsonIgnore
	private byte[] image;

	@OneToMany(mappedBy = "location")
	@JsonIgnore
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Set<Course> getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(Set<Course> courseSet) {
		this.courseSet = courseSet;
	}

}
