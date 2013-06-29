package test;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.StringContains.containsString;

import java.util.Collection;

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

@ContextConfiguration(locations = { "classpath:/META-INF/application-test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class GalaxyServiceTests {
	@Autowired
	private GalaxyService galaxyService;
	@Autowired
	public Neo4jTemplate template;

//	@Rollback(false)
//	@BeforeTransaction
//	public void cleanUpGraph() {
//		Neo4jHelper.cleanDb(template);
//	}

	@Test
    public void shouldAllowDirectWorldCreation() {
		assertEquals(0, galaxyService.getNumberOfWorlds());
		World myWorld = galaxyService.createWorld("mine", 0);
		template.save(myWorld);
        assertEquals(1, galaxyService.getNumberOfWorlds());
        
        Iterable<World> foundWorlds = galaxyService.getAllWorlds();
        World mine = foundWorlds.iterator().next();
        template.save(mine);
        assertEquals(myWorld.getName(), mine.getName());
    
		
		
		
		// GraphDatabaseService graphDatabaseService = new
		// GraphDatabaseFactory().newEmbeddedDatabase( "target/graph.db" );
		// Node abc = graphDatabaseService.createNode();
		// abc.setProperty("name","ben");
		// World myWorld = galaxyService.createWorld("mine", 0);
		// Iterable<World> foundWorlds = galaxyService.getAllWorlds();
		// World mine = foundWorlds.iterator().next();
		// assertEquals(myWorld.getName(), mine.getName());
	}
@Test
public void shouldFindWorldsByName() {
	galaxyService.makeSomeWorlds();
	
    for(World world : galaxyService.getAllWorlds()) {
    	World foundWorld = galaxyService.findWorldByName(world.getName()); 
        assertNotNull(foundWorld);
    }
}
@SuppressWarnings("unchecked")
public void shouldFindWorldsWith1Moon() {
    galaxyService.makeSomeWorlds();
    
    for(World worldWithOneMoon : galaxyService.findAllByNumberOfMoons(1)) {
    	assertThat(
    			worldWithOneMoon.getName(), 
    			is(anyOf(containsString("Earth"), containsString("Midgard"))));
    }
}

@Test
public void shouldNotFindKrypton() {
	galaxyService.makeSomeWorlds();
	World krypton = galaxyService.findWorldByName("Krypton");
	assertNull(krypton);
}
@Test
public void shouldFindAllWorlds() {
    Collection<World> madeWorlds = galaxyService.makeSomeWorlds();
    Iterable<World> foundWorlds = galaxyService.getAllWorlds();

    int countOfFoundWorlds = 0;
    for(World foundWorld : foundWorlds) {
        assertTrue(madeWorlds.contains(foundWorld));
        countOfFoundWorlds++;
    }

    assertEquals(madeWorlds.size(), countOfFoundWorlds);
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

