package org.sideproject.simplestore.repository;


import java.util.Optional;

import org.sideproject.simplestore.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
	/**
	 * 
	 * @param userName
	 * @return
	 */
	Optional<User> findByUserNameIgnoreCase(String userName);
}
