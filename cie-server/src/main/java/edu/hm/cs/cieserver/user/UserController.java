package edu.hm.cs.cieserver.user;

import edu.hm.cs.cieserver.course.Course;
import edu.hm.cs.cieserver.course.CourseRepository;
import edu.hm.cs.cieserver.util.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

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

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private MailService mailService;

	private static Map<String, String> passwordResetCodeMap = new HashMap<>();

	@PutMapping(path = "/firebase-token")
	public void setFirebaseToken(@RequestBody String firebaseToken, Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		user.setFirebaseToken(firebaseToken);

		userRepository.save(user);
	}

	@GetMapping(path = "/reset-password/{email}")
	public void requestPasswordResetForEmail(@PathVariable("email") String email) {
		User user = (User) userDetailsService.loadUserByUsername(email);

		// If user could be found, it exists.

		// Generate random code for password reset.
		String uuid = UUID.randomUUID().toString().replace("-", "");
		passwordResetCodeMap.put(email, uuid);

		mailService.send(user.getEmail(), "Password reset", "Hey there, you wanted to reset your password. Here is the code you need for that: " + uuid);
	}

	@GetMapping(path = "/reset-password/{email}/{code}/{newPassword}")
	public ResponseEntity<?> resetPassword(@PathVariable("email") String email, @PathVariable("code") String code, @PathVariable("newPassword") String password) {
		HttpStatus status = HttpStatus.OK;

		User user = (User) userDetailsService.loadUserByUsername(email);

		String neededCode = passwordResetCodeMap.get(email);

		if (neededCode.equals(code)) {
			passwordResetCodeMap.remove(email);
			if (password != null && password.length() > 4) {
				User existing = userRepository.findByEmail(email);

				existing.setPassword(passwordEncoder.encode(password));

				userRepository.save(existing);
			} else {
				status = HttpStatus.BAD_REQUEST;
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(null, status);
	}

	@GetMapping(path = "/selected-course/{courseId}")
	public Set<User> findUsersWhoSelectedCourse(@PathVariable("courseId") Long courseId) {
		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			return course.get().getSelectedBy();
		}

		return Collections.emptySet();
	}

	@GetMapping(path = "/favorized-course/{courseId}")
	public Set<User> findUsersWhoFavorizedCourse(@PathVariable("courseId") Long courseId) {
		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			return course.get().getFavorizedBy();
		}

		return Collections.emptySet();
	}

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
	public ResponseEntity<User> update(@RequestBody User user, Principal principal) {
		User existing = findOne(user.getId());
		User userRequestingUpdate = (User) userDetailsService.loadUserByUsername(principal.getName());

		if (existing != null) {
			if (user.getIsAdministrator() && !userRequestingUpdate.getId().equals(existing.getId())) {
				// Admins can only be updated by themselves, not by other admins.
				return new ResponseEntity<>(new User(), HttpStatus.FORBIDDEN);
			}

			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setEmail(user.getEmail());
			existing.setIsAdministrator(user.getIsAdministrator());

			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				existing.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			return new ResponseEntity<>(userRepository.save(existing), HttpStatus.OK);
		}

		return new ResponseEntity<>(new User(), HttpStatus.FORBIDDEN);
	}

	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") Long id, Principal principal) {
		HttpStatus status = HttpStatus.OK;

		if (principal != null) {
			User userRequestingDeletion = (User) userDetailsService.loadUserByUsername(principal.getName());

			Optional<User> userToDelete = userRepository.findById(id);

			if (userToDelete.isPresent()) {
				User user = userToDelete.get();

				if (user.getIsAdministrator()) {
					// Users who are admins can only be deleted by themselves and not by other admins.
					if (user.getId().equals(userRequestingDeletion.getId())) {
						userRepository.delete(user);
					} else {
						status = HttpStatus.FORBIDDEN;
					}
				} else {
					userRepository.delete(user);
				}
			}
		}

		return new ResponseEntity<>(null, status);
	}

	@GetMapping
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
