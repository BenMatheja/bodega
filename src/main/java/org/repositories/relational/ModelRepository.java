package org.repositories.relational;


import org.model.relational.Model;

public interface ModelRepository {
	Model findById(Long id);

}
