package org.model;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
@NodeEntity
public class User extends AbstractEntity {
	
	@Indexed(unique=true)
	long UserId;
	String firstname;
	String lastname;
	Set<Project> projects;
	
	public long getUserId() {
		return UserId;
	}
	public void setUserId(long userId) {
		UserId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	
	public User(String firstname, String lastname){
		this.firstname = firstname;
		this.lastname = lastname;
		
	}

	
}
