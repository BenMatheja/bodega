package org.relational.repositories;

import org.relational.model.Model;
import org.springframework.data.repository.Repository;

public interface ModelRepository extends Repository<Model, Long> {
	Model findById(Long id);

	Model save(Model model);

	Model findByTitle(String title);

	Iterable<Model> findAll();

}
