package org.model;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

@NodeEntity
public class Vertex extends AbstractEntity {
	String caption;
	
	@RelatedTo(type="IS_PART_OF")
	@Indexed(unique = true)
	private Model model;
	
	public Vertex(String caption){
		this.caption = caption;
	}
	@RelatedTo(type="CONNECTED", direction = Direction.OUTGOING)
	private Vertex NextVertex;

}
