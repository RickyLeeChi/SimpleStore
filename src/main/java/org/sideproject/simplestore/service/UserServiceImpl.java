package org.sideproject.simplestore.service;

import java.util.Optional;

import org.sideproject.simplestore.entity.User;
import org.sideproject.simplestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findByUserNameIgnoreCase(String userName){
		return userRepository.findByUserNameIgnoreCase(userName);
	}
}
