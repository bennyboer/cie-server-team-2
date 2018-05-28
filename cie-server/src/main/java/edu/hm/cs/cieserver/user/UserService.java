package edu.hm.cs.cieserver.user;

import java.util.List;

public interface UserService {

	User create(User user);

	User delete(Long id);

	List<User> findAll();

	User findById(Long id);

	User update(User user);

}
