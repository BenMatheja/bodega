package org.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;
@NodeEntity
public class Model extends AbstractEntity {
	@Indexed(indexType = IndexType.FULLTEXT, indexName ="searchByTitle") //Search a Model via title
	String title;

	@RelatedTo(type = "IS_AN") //define the rel to Language, (Rechnungsprüfung IS_AN EPK)
	private Language language;
	
	@RelatedTo(type ="HAS_MANY", elementClass = Edge.class) //define the rel to the edges, (Rechnungsprüfung HAS_MANY Edges)
	Set<Edge> edges;
	
	public Model(String title){
		this.title = title;

	}
	private Model(){ /*no-arg constructor for SDN bean generation */}
	
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void addEdge(Edge e){
		edges.add(e);
	}
	
	public void setEdges(Set<Edge> allEdges){
		this.edges = allEdges;
	}
	
	public Set<Edge> getAllEdges(){
		return this.edges;
	}
}
