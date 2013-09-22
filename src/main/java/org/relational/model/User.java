package org.relational.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import org.relational.model.Project;

public class User extends AbstractEntity {
	
	long UserId;
	@Column(name="Firstname", nullable = false)
	String firstname;
	@Column(name="Lastname", nullable = false)
	String lastname;
	
//	@ManyToMany
//	Set<Project> projects;
	
	public User(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

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

//	PUBLIC SET<PROJECT> GETPROJECTS() {
//		RETURN PROJECTS;
//	}
//
//	PUBLIC VOID SETPROJECTS(SET<PROJECT> PROJECTS) {
//		THIS.PROJECTS = PROJECTS;
//	}

}
