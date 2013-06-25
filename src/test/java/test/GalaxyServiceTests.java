package test;

import static org.junit.Assert.*;
import javax.validation.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import components.GalaxyService;
import components.World;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class GalaxyServiceTests {
	@Autowired
	private GalaxyService galaxyService;
	@Autowired
	public Neo4jTemplate template;

	@Rollback(false)
	@BeforeTransaction
	public void cleanUpGraph() {
		Neo4jHelper.cleanDb(template);
	}

	@Test
	public void allowWorldCreation() {
		World abc = new World("Erde", 1);
		template.save(abc);
		World myWorld = galaxyService. findWorldByName("Erde");
		assertEquals(abc.getName(), myWorld.getName());
		
		
		// GraphDatabaseService graphDatabaseService = new
		// GraphDatabaseFactory().newEmbeddedDatabase( "target/graph.db" );
		// Node abc = graphDatabaseService.createNode();
		// abc.setProperty("name","ben");
		// World myWorld = galaxyService.createWorld("mine", 0);
		// Iterable<World> foundWorlds = galaxyService.getAllWorlds();
		// World mine = foundWorlds.iterator().next();
		// assertEquals(myWorld.getName(), mine.getName());
	}
}

// graphDatabaseService.createNode();
// forrest.setProperty("title","Forrest Gump");
// forrest.setProperty("year",1994);
// gds.index().forNodes("movies").add(forrest,"id",1);
//
// Node tom=gds.createNode();
// tom.setProperty("name","Tom Hanks");
//
// Relationship role=tom.createRelationshipTo(forrest,ACTS_IN);
// role.setProperty("role","Forrest");
//
// Node movie=gds.index().forNodes("movies").get("id",1).getSingle();
// assertEquals("Forrest Gump", movie.getProperty("title"));
// for (Relationship role : movie.getRelationships(ACTS_IN,INCOMING)) {
// Node actor=role.getOtherNode(movie);
// assertEquals("Tom Hanks", actor.getProperty("name"));
// assertEquals("Forrest", role.getProperty("role"));
// }

