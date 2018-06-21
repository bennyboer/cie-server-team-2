package edu.hm.cs.cieserver.statistics;

import edu.hm.cs.cieserver.campus.CampusRepository;
import edu.hm.cs.cieserver.course.CourseRepository;
import edu.hm.cs.cieserver.department.DepartmentRepository;
import edu.hm.cs.cieserver.lecturer.LecturerRepository;
import edu.hm.cs.cieserver.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/statistics"})
public class StatisticsController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CampusRepository campusRepository;

	@Autowired
	private LecturerRepository lecturerRepository;

	@GetMapping(path = "/count")
	public ItemCountStatistics getItemCountStatistics() {
		return new ItemCountStatistics(courseRepository.count(),
				lecturerRepository.count(),
				campusRepository.count(),
				departmentRepository.count(),
				userRepository.count());
	}

}
