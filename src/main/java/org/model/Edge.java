package org.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "CONNECTED")
public class Edge extends AbstractEntity {

	@StartNode
	Vertex start;
	@EndNode
	Vertex end;
	String caption;

	public Edge(Vertex Start, Vertex end, String caption) {
		this.start = Start;
		this.end = end;
		this.caption = caption;
	}

	public Edge() {
	}

	public Vertex getStart() {
		return this.start;
	}

	public Vertex getEnd() {
		return this.end;
	}

	public String getCaption() {
		return this.caption;
	}

}
