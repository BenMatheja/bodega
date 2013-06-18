package Repositories;
import model.Model;


import org.springframework.data.neo4j.repository.* ;

public interface ModelRepository extends GraphRepository <Model>{

	Model findOne(Long nodeId);
	
	<C extends Model> C save(C model);
	
	Model findByTitle(String title);

}
