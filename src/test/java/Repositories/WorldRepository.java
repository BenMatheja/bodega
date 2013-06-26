package Repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import components.World;

public interface WorldRepository extends GraphRepository<World> {}