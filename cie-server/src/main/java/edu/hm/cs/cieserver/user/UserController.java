package edu.hm.cs.cieserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api"})
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	@GetMapping(path = {"/{id}"})
	public User findOne(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@PutMapping
	public User update(@RequestBody User user) {
		return userService.update(user);
	}

	@DeleteMapping(path = {"/{id}"})
	public User delete(@PathVariable("id") Long id) {
		return userService.delete(id);
	}

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}
}
