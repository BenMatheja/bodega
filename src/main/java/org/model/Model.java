package org.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;

public class Model extends AbstractEntity {
	@Indexed(unique = true)
	String id;
	String title;

	@RelatedTo(type = "IS_AN")
	private Language language;

	@RelatedTo(type = "CONSISTS_OF")
	private List<Edge> Edges = new ArrayList<Edge>();

	@RelatedTo(type = "AGGREGATES")
	private List<Vertex> Vertices = new ArrayList<Vertex>();

	public Model(Language language, String title) {
		this.language = language;
		this.title = title;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<Edge> getEdges() {
		return Edges;
	}

	public void setEdges(List<Edge> edges) {
		Edges = edges;
	}

	public List<Vertex> getVertices() {
		return Vertices;
	}

	public void setVertices(List<Vertex> vertices) {
		Vertices = vertices;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
}
