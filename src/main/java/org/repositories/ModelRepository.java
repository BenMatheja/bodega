package org.repositories;


import org.model.Model;
import org.springframework.data.neo4j.repository.* ;
import org.model.Vertex;
import org.model.Language;
import org.model.Edge;

public interface ModelRepository extends GraphRepository <Model>{

Model findById(Long id);
}
