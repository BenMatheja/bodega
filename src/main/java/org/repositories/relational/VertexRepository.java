package org.repositories.relational;

import org.model.relational.Vertex;
import org.springframework.data.repository.Repository;

public interface VertexRepository extends Repository<Vertex, Long> {
	Vertex save(Vertex vertex);

}
