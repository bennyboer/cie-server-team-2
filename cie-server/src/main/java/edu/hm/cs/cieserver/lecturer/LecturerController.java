package edu.hm.cs.cieserver.lecturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling lecturer purposes.
 */
@RestController
@RequestMapping({"/api/lecturers"})
public class LecturerController {

	@Autowired
	private LecturerRepository lecturerRepository;

	@GetMapping(path = {"/{id}"})
	public Lecturer findOne(@PathVariable("id") Long id) {
		return lecturerRepository.findById(id).get();
	}

	@GetMapping
	public List<Lecturer> findAll() {
		return lecturerRepository.findAll();
	}

	@PostMapping
	public Lecturer create(@RequestBody Lecturer course) {
		return lecturerRepository.save(course);
	}

	@PutMapping
	public Lecturer update(@RequestBody Lecturer course) {
		return lecturerRepository.save(course);
	}

	@DeleteMapping(path = {"/{id}"})
	public void delete(@PathVariable("id") Long id) {
		lecturerRepository.deleteById(id);
	}

}
