package edu.hm.cs.cieserver.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the user entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	boolean existsByEmail(String email);

}
