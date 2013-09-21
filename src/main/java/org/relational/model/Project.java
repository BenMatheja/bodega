package org.relational.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hsqldb.rights.User;

@Entity
public class Project extends AbstractEntity {
	
	private String title;
	
	@OneToMany
	@ElementCollection
	private Set<Model> models = new HashSet<Model>();
	
	private User owner;
	
	

}
