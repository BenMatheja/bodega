package org.model;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "CONNECTED")
public class Edge extends AbstractEntity {
String caption;

@StartNode Vertex v1;
@EndNode Vertex v2;

public Edge(Vertex start, Vertex end, String caption ){
	this.v1 =  start;
	this.v2 = end;
	this.caption = caption;	
}
	

}
