package org.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
@NodeEntity
public class Model extends AbstractEntity {
	@Indexed
	String title;

	@RelatedTo(type = "IS_AN")
	private Language language;

	public Model(String title){
		this.title = title;

	}
	private Model(){
		
	}
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
}
