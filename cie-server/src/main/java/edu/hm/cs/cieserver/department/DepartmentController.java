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
		return departmentRepository.save(course);
	}

	@PutMapping
	public Department update(@RequestBody Department course) {
		return departmentRepository.save(course);
	}

	@DeleteMapping(path = {"/{number}"})
	public void delete(@PathVariable("number") Long number) {
		departmentRepository.deleteById(number);
	}

}
