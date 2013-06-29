package org.model;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "CONNECTED")
public class Edge  {
String caption;

@StartNode private Vertex v1;
@EndNode private Vertex v2;



}
