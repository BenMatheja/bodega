package org.model;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
/**
 * represents a language entitiy
 * is indexable through title
 * @author ben
 *
 */
@NodeEntity
public class Language extends AbstractEntity {
@Indexed(unique=true)	
String title;

private String description;

@RelatedTo(elementClass = Model.class, type = "SPEAKS")
Set<Model> models = new HashSet<Model>();

public Language(String title, String description){
	this.title = title;
	this.description = description;
}
private Language(){
	
}

public void setTitle(String title){
	this.title = title;
}
public String getTitle(){
	return this.title;
}
public void setDescription(String desc){
	this.description = desc;
}
public String getDescription(){
	return this.description;
}
public void addModel(Model model){
	models.add(model);
}
}
