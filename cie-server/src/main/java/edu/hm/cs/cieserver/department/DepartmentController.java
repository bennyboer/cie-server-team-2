package edu.hm.cs.cieserver.department;

import edu.hm.cs.cieserver.notification.NotificationRequest;
import edu.hm.cs.cieserver.notification.NotificationService;
import edu.hm.cs.cieserver.util.Switches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller handling department purposes.
 */
@RestController
@RequestMapping({"/api/departments"})
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private NotificationService notificationService;

	@GetMapping(path = {"/{number}"})
	public Department findOne(@PathVariable("number") Long number) {
		return departmentRepository.findById(number).get();
	}

	@GetMapping
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@PostMapping
	public Department create(@RequestBody Department course) {
		Department d = departmentRepository.save(course);

		notifyOfDepartmentChanges();

		return d;
	}

	@PutMapping
	public Department update(@RequestBody Department course) {
		Department d = departmentRepository.save(course);

		notifyOfDepartmentChanges();

		return d;
	}

	@DeleteMapping(path = {"/{number}"})
	public void delete(@PathVariable("number") Long number) {
		departmentRepository.deleteById(number);

		notifyOfDepartmentChanges();
	}

	/**
	 * Notify all users of the department changes.
	 */
	private void notifyOfDepartmentChanges() {
		if (Switches.ENABLE_NOTIFICATIONS) {
			List<Department> departments = departmentRepository.findAll();

			NotificationRequest request = new NotificationRequest("department_changes", "changes", departments, Optional.of("/topics/changes"));

			notificationService.send(request);
		}
	}


}
