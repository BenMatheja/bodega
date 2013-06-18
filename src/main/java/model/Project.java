package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.annotation.*;

@NodeEntity
public class Project extends AbstractEntity {
	@Indexed(unique = true)
	String id;
	String title;

	@RelatedTo(type = "IS_PART_OF")
	private List<Model> Models = new ArrayList<Model>();

	@RelatedTo(type = "IS_OWNER")
	private User owner;

	public Project(String title, String id, User owner) {
		this.title = title;
		this.id = id;
		this.owner = owner;

	}

}
