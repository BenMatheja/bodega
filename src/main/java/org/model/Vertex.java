package org.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;

/**
 * Vertex object represents vertex in a bpm model caption references
 * 
 * @author ben
 */
@NodeEntity
public class Vertex extends AbstractEntity {
	private String caption;

	@SuppressWarnings("unused")
	private Vertex() {/* no-arg constructor for sdn bean creation */
	}

	public Vertex(String caption) {
		this.setCaption(caption);
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

}
