package edu.hm.cs.cieserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/users"})
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public User create(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping(path = {"/{id}"})
	public User findOne(@PathVariable("id") Long id) {
		return userRepository.findById(id).get();
	}

	@PutMapping
	public User update(@RequestBody User user) {
		return userRepository.save(user);
	}

	@DeleteMapping(path = {"/{id}"})
	public User delete(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);

		user.ifPresent(u -> userRepository.delete(u));

		return user.get();
	}

	@GetMapping
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
