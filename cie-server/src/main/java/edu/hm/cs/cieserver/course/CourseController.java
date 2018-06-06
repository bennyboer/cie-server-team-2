package edu.hm.cs.cieserver.course;

import edu.hm.cs.cieserver.user.User;
import edu.hm.cs.cieserver.user.UserDetailsServiceImpl;
import edu.hm.cs.cieserver.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Controller handling course purposes.
 */
@RestController
@RequestMapping({"/api/courses"})
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@GetMapping(path = "/selected/{userId}")
	public Set<Course> findSelectedCoursesByUserId(@PathVariable("userId") Long userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get().getSelectedCourses();
		}

		return Collections.emptySet();
	}

	@GetMapping(path = "/selected")
	public Set<Course> findSelectedCoursesForLoggedOnUser(Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		return user.getSelectedCourses() != null ? user.getSelectedCourses() : Collections.emptySet();
	}

	@GetMapping(path = "/favorized/{userId}")
	public Set<Course> findFavorizedCoursesByUserId(@PathVariable("userId") Long userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get().getFavorizedCourses();
		}

		return Collections.emptySet();
	}

	@GetMapping(path = "/favorized")
	public Set<Course> findFavorizedCoursesForLoggedOnUser(Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		return user.getFavorizedCourses() != null ? user.getFavorizedCourses() : Collections.emptySet();
	}

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
