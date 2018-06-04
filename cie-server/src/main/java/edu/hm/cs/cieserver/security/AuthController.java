package edu.hm.cs.cieserver.security;

import edu.hm.cs.cieserver.security.login.JWTAuthenticationResponse;
import edu.hm.cs.cieserver.security.login.LoginRequest;
import edu.hm.cs.cieserver.security.signup.SignUpRequest;
import edu.hm.cs.cieserver.user.User;
import edu.hm.cs.cieserver.user.UserDetailsServiceImpl;
import edu.hm.cs.cieserver.user.UserRepository;
import edu.hm.cs.cieserver.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Controller handling authentication purposes.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JWTTokenProvider tokenProvider;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	/**
	 * Called at the start of the management interface.
	 * This method makes sure that at least one admin is available.
	 * Otherwise it creates a new one and returns it.
	 *
	 * @return null or a new user who is the new administrator.
	 */
	@GetMapping
	public User initialize() {
		List<User> users = userRepository.findAll();

		if (users != null) {
			if (users.stream().filter(User::getIsAdministrator).count() == 0) {
				// No admins available -> Create new one.
				User admin = new User();

				admin.setFirstName("Administrator");
				admin.setLastName("");
				admin.setEmail("admin");
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setIsAdministrator(true);

				return userRepository.save(admin);
			}
		}

		return null;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		User user = (User) userDetailsService.loadUserByUsername(loginRequest.getEmail());

		return ResponseEntity.ok(new JWTAuthenticationResponse(jwt, user.getIsAdministrator()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new APIResponse(false, "User with email already exists."), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User();

		user.setEmail(signUpRequest.getEmail());
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());

		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new APIResponse(true, "User registered successfully"));
	}

}
