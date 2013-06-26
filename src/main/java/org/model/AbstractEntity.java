package org.model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import org.springframework.data.neo4j.annotation.GraphId;

public class AbstractEntity  {
	@GraphId
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long NodeId;
	
	public long getNodeId(){
		return this.NodeId;
	}

}
