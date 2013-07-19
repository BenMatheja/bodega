package org.model.relational;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Vertex extends AbstractEntity {
	@Column(nullable=false)
	private String caption;

	public Vertex() {
	};

	public Vertex(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}
