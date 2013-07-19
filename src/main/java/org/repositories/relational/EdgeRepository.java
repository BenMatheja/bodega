package org.repositories.relational;


import org.model.relational.Edge;
import org.springframework.data.repository.Repository;

public interface EdgeRepository extends Repository<Edge, Long> {
	Edge save(Edge edge);

}
