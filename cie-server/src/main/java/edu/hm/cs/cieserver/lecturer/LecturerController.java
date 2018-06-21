package edu.hm.cs.cieserver.lecturer;

import edu.hm.cs.cieserver.notification.NotificationRequest;
import edu.hm.cs.cieserver.notification.NotificationService;
import edu.hm.cs.cieserver.util.Switches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller handling lecturer purposes.
 */
@RestController
@RequestMapping({"/api/lecturers"})
public class LecturerController {

	@Autowired
	private LecturerRepository lecturerRepository;

	@Autowired
	private NotificationService notificationService;

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
		Lecturer l = lecturerRepository.save(course);

		notifyOfLecturerChanges();

		return l;
	}

	@PutMapping
	public Lecturer update(@RequestBody Lecturer course) {
		Lecturer l = lecturerRepository.save(course);

		notifyOfLecturerChanges();

		return l;
	}

	@DeleteMapping(path = {"/{id}"})
	public void delete(@PathVariable("id") Long id) {
		lecturerRepository.deleteById(id);

		notifyOfLecturerChanges();
	}

	/**
	 * Notify all users of the lecturer changes.
	 */
	private void notifyOfLecturerChanges() {
		if (Switches.ENABLE_NOTIFICATIONS) {
			List<Lecturer> lecturers = lecturerRepository.findAll();

			NotificationRequest request = new NotificationRequest("lecturer_changes", "changes", lecturers, Optional.of("/topics/changes"));

			notificationService.send(request);
		}
	}

}
