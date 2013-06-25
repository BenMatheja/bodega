package test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;



public class test {

	public static void main(String args []){
		GraphDatabaseService	 graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase( "target/graph.db" );
		Node abc = graphDatabaseService.createNode();
		abc.setProperty("name","ben");
	
		
	
//	graphDatabaseService.createNode();
//	forrest.setProperty("title","Forrest Gump");
//	forrest.setProperty("year",1994);
//	gds.index().forNodes("movies").add(forrest,"id",1);
//
//	Node tom=gds.createNode();
//	tom.setProperty("name","Tom Hanks");
//
//	Relationship role=tom.createRelationshipTo(forrest,ACTS_IN);
//	role.setProperty("role","Forrest");
//
//	Node movie=gds.index().forNodes("movies").get("id",1).getSingle();
//	assertEquals("Forrest Gump", movie.getProperty("title"));
//	for (Relationship role : movie.getRelationships(ACTS_IN,INCOMING)) {
//	    Node actor=role.getOtherNode(movie);
//	    assertEquals("Tom Hanks", actor.getProperty("name"));
//	    assertEquals("Forrest", role.getProperty("role"));
//	}
	}
	
}
