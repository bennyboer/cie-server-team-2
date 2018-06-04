package edu.hm.cs.cieserver.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling department purposes.
 */
@RestController
@RequestMapping({"/api/departments"})
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping(path = {"/{id}"})
	public Department findOne(@PathVariable("id") Long id) {
		return departmentRepository.findById(id).get();
	}

	@GetMapping
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@PostMapping
	public Department create(@RequestBody Department course) {
		return departmentRepository.save(course);
	}

	@PutMapping
	public Department update(@RequestBody Department course) {
		return departmentRepository.save(course);
	}

	@DeleteMapping(path = {"/{id}"})
	public void delete(@PathVariable("id") Long id) {
		departmentRepository.deleteById(id);
	}

}
