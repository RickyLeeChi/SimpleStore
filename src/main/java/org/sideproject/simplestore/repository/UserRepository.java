package org.sideproject.simplestore.repository;

import org.sideproject.simplestore.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

}
