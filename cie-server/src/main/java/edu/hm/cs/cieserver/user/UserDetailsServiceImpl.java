package edu.hm.cs.cieserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Load a user by id.
	 *
	 * @param id to load user by
	 * @return loaded user
	 */
	public UserDetails loadUserById(Long id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UsernameNotFoundException(String.format("Could not load user by id: %d", id));
		}

		return user.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}
}
