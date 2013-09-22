package org.relational.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.relational.model.User;

@Entity
public class Project extends AbstractEntity {
	
	private String title;
	
	@OneToMany
	@ElementCollection
	private Set<Model> models = new HashSet<Model>();
	
//	@OneToOne
//	private User owner;
	
	

}
