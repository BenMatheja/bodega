package org.relational.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.relational.model.Edge;
import org.relational.model.Language;

@Entity
public class Model extends AbstractEntity {
	@Column(unique = true)
	String title;

	@ManyToOne
	Language language;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Edge> edges = new HashSet<Edge>();

	public Model(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Set<Edge> getAllEdges() {
		return edges;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}

}
