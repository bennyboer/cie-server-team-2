package edu.hm.cs.cieserver.course.date;

import edu.hm.cs.cieserver.course.Course;
import edu.hm.cs.cieserver.course.CourseRepository;
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
 * Controller handling course appointment purposes.
 */
@RestController
@RequestMapping({"/api/course-appointments"})
public class CourseAppointmentController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseAppointmentRepository courseAppointmentRepository;

	@PostMapping(path = "/{courseId}")
	public List<CourseAppointment> update(@PathVariable("courseId") Long courseId, @RequestBody List<CourseAppointment> courseAppointments) {
		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			Course c = course.get();

			for (CourseAppointment appointment : courseAppointments) {
				appointment.setCourse(c);
			}
		}

		return courseAppointmentRepository.saveAll(courseAppointments);
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		courseAppointmentRepository.deleteById(id);
	}

}
