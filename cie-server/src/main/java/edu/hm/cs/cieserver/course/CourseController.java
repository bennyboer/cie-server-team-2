package edu.hm.cs.cieserver.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller handling course purposes.
 */
@RestController
@RequestMapping({"/api/courses"})
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping(path = {"/{id}"})
	public Course findOne(@PathVariable("id") Long id) {
		return courseRepository.findById(id).get();
	}

	@GetMapping
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@PostMapping
	public Course create(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@PutMapping
	public Course update(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping(path = {"/{id}"})
	public Course delete(@PathVariable("id") Long id) {
		Optional<Course> course = courseRepository.findById(id);

		course.ifPresent(u -> courseRepository.delete(u));

		return course.get();
	}

}
