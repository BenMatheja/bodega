package org.relational.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.relational.model.Vertex;

@Entity
public class Edge extends AbstractEntity {
	@ManyToOne
	Vertex start;
	@ManyToOne
	Vertex end;
	@Column(name="CAPTION",nullable = false)
	String caption;

	public Edge() {

	}

	public Edge(Vertex start, Vertex end, String caption) {
		this.start = start;
		this.caption = caption;
		this.end = end;
	}

	public Vertex getStart() {
		return start;
	}

	public void setStart(Vertex start) {
		this.start = start;
	}

	public Vertex getEnd() {
		return end;
	}

	public void setEnd(Vertex end) {
		this.end = end;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}
