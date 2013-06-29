package org.repositories;


import org.model.Model;
import org.springframework.data.neo4j.repository.* ;

public interface ModelRepository extends GraphRepository <Model>{

Model findById(Long id);
}
