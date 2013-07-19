package org.repositories.relational;

import org.model.relational.Model;
import org.springframework.data.repository.Repository;

public interface ModelRepository extends Repository<Model, Long> {
	Model findById(Long id);

	Model save(Model model);

	Model findByTitle(String title);

	Iterable<Model> findAll();

}
