package model;

import org.springframework.data.neo4j.annotation.*;

@NodeEntity
public class Vertex extends AbstractEntity {
	String caption;
	
	@RelatedTo(type="IS_PART_OF")
	private Model model;
	
	public Vertex(String caption){
		this.caption = caption;
	}
	

}
