package edu.hm.cs.cieserver.campus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling campus purposes.
 */
@RestController
@RequestMapping({"/api/locations"})
public class CampusController {

	@Autowired
	private CampusRepository campusRepository;

	@Value("${app.cie-server.maps-api-key}")
	private String mapsApiKey;

	@GetMapping(path = "/maps-api-key")
	public String getMapsApiKey() {
		return mapsApiKey;
	}

	@GetMapping(path = {"/{id}"})
	public Campus findOne(@PathVariable("id") Long id) {
		return campusRepository.findById(id).get();
	}

	@GetMapping
	public List<Campus> findAll() {
		return campusRepository.findAll();
	}

	@PostMapping
	public Campus create(@RequestBody Campus course) {
		return campusRepository.save(course);
	}

	@PutMapping
	public Campus update(@RequestBody Campus course) {
		return campusRepository.save(course);
	}

	@DeleteMapping(path = {"/{id}"})
	public void delete(@PathVariable("id") Long id) {
		campusRepository.deleteById(id);
	}

}
