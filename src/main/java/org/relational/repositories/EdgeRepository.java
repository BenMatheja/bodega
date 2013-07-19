package org.relational.repositories;


import org.relational.model.Edge;
import org.springframework.data.repository.Repository;

public interface EdgeRepository extends Repository<Edge, Long> {
	Edge save(Edge edge);

}
