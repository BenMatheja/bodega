package org.relational.repositories;

import java.util.List;

import org.relational.model.Model;
import org.springframework.data.repository.Repository;


public interface ModelRepository extends Repository<Model, Long> {
	Model findById(Long id);
	
	Model findByUid(int uid);

	Model save(Model model);

	public Model findByTitle(String title);

	Iterable<Model> findAll();

}
