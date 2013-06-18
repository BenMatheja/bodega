package model;
import org.springframework.data.neo4j.annotation.GraphId;

public class AbstractEntity  {
	@GraphId
	private long NodeId;
	
	public long getNodeId(){
		return this.NodeId;
	}

}
