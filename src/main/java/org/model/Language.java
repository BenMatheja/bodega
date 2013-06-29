package org.model;
import org.springframework.data.neo4j.annotation.*;

@NodeEntity
public class Language extends AbstractEntity {
@Indexed(unique=true)	
private String title;

private String description;

public Language(String title, String description){
	this.title = title;
	this.description = description;

	
}


}
