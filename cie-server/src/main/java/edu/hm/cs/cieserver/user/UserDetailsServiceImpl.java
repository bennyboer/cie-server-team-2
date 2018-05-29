package edu.hm.cs.cieserver.user;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// This method is used by JWTAuthenticationFilter
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();

		if (user == null) {
			throw new UsernameNotFoundException(String.format("Could not load user by id: %d", id));
		}

		return user;
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
