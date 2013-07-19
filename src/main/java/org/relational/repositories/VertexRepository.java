package org.relational.repositories;

import org.relational.model.Vertex;
import org.springframework.data.repository.Repository;

public interface VertexRepository extends Repository<Vertex, Long> {
	Vertex save(Vertex vertex);

}
