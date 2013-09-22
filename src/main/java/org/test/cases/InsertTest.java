package org.test.cases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.components.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.Language;
import org.model.Model;
import org.model.Vertex;
import org.model.Edge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
/**
 * NoSQL
 * @author ben
 *
 */
public class InsertTest extends AbstractTestcase {


	@Before
	public void setUp() {
	}

	@Test
	public void insert100() {
		this.insertQuants(100, 50, 75, 1, 10, "insert100"); //models, vertices, edges
	}

	@Test
	public void insert500() {
		this.insertQuants(500, 100, 125, 1, 10, "insert500");
	}

//	@Test
	public void insert1000() {
		this.insertQuants(1000, 125, 175, 1, 10, "insert1000");
	}
	
//	@Test
	public void insert1500() {
		this.insertQuants(1500, 200, 325, 1,10, "insert1500" );
	}
	
}
