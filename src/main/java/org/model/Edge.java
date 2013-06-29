package org.model;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "CONNECTED")
public class Edge extends AbstractEntity {

@StartNode Vertex v1;
@EndNode Vertex v2;
String caption;

public Edge(Vertex Start, Vertex end, String caption){
	this.v1 = Start;
	this.v2 = end;
	this.caption = caption;
}
public Edge(){
}

public Vertex getStart(){
	return this.v1;
}
public Vertex getEnd(){
	return this.v2;
}


}
