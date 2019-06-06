package org.sideproject.simplestore.repository;

import java.util.Optional;

import org.sideproject.simplestore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Optional<Category> findByCategory(String category);
}
