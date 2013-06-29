package org.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
@NodeEntity
public class Model extends AbstractEntity {
	String title;

	@RelatedTo(type = "IS_AN")
	private Language language;

	public Model(Language language, String title) {
		this.language = language;
		this.title = title;

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
