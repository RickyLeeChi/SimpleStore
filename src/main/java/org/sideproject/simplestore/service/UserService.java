package org.sideproject.simplestore.service;

import java.util.Optional;

import org.sideproject.simplestore.entity.User;

public interface UserService {
	public User save(User user);
	public Optional<User> findByUserNameIgnoreCase(String userName);
}
