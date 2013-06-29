package org.model;

import java.util.Collection;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
/**
 * Vertex object
 * @author ben
 * represents vertex in a bpm model
 * caption
 * references to OutgoingEdges
 */
@NodeEntity
public class Vertex extends AbstractEntity {
	@RelatedTo(type="IS_PART_OF")
	@Indexed(unique = true)
	Model model;
	@RelatedToVia(type="CONNECTED", direction = Direction.OUTGOING, elementClass = Edge.class)
	Collection<Edge> OutgoingEdges;
	
	private String caption;
	
	private Vertex(){}
	public Vertex(String caption){
		this.setCaption(caption);
	}
	
	public void setModel(Model model){
		this.model = model;
	}
	public Model getModel(){
		return this.model;
	}
	
	public Edge connectWith(Vertex v1, String caption){
		Edge e = new Edge(this, v1, caption);
		OutgoingEdges.add(e);
		return e;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	

}
