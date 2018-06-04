package edu.hm.cs.cieserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Controller handling user purposes.
 */
@RestController
@RequestMapping({"/api/users"})
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@GetMapping(path = "/current")
	public User currentUser(Principal principal) {
		if (principal != null) {
			return (User) userDetailsService.loadUserByUsername(principal.getName());
		}

		return null;
	}

	@PostMapping
	public User create(@RequestBody User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			return null;
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	@GetMapping(path = {"/{id}"})
	public User findOne(@PathVariable("id") Long id) {
		return userRepository.findById(id).get();
	}

	@PutMapping
	public User update(@RequestBody User user) {
		User existing = findOne(user.getId());

		if (existing != null) {
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setEmail(user.getEmail());
			existing.setIsAdministrator(user.getIsAdministrator());

			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				existing.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			return userRepository.save(existing);
		}

		return null;
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
