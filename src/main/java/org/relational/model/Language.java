package org.relational.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.relational.model.Model;

@Entity
public class Language extends AbstractEntity {
	@Column(name="TITLE",nullable = false)
	String title;
	
	@Column(name="DESC",nullable = false)
	String description;
	
	@ElementCollection
	@OneToMany
	Set<Model> models = new HashSet<Model>();

	public Language(String title, String description) {
		this.title = title;
		this.description = description;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}

	public void addModel(Model model) {
		models.add(model);
	}

}
