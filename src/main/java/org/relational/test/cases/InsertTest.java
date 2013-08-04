package org.relational.test.cases;

//Domain objects
import static org.junit.Assert.assertNotNull;

import org.relational.model.Language;
import org.relational.model.Model;
import org.relational.model.Edge;
import org.relational.model.Vertex;
//Components
import org.relational.components.ModelService;
import org.relational.repositories.LanguageRepository;
//Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


//Java utils
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


//junit
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context-relational.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)// persist it to file
/**
 * Relational
 * @author ben
 *
 */
public class InsertTest extends AbstractTestCase {
	@Autowired
	ModelService modelservice;
	@Autowired
	LanguageRepository languageRepository;

	@Before
	public void setUp() {

	}

	@Test
	public void insert100() {
		this.insertQuants(100, 50, 75, 1, 10); //models, vertices, edges
		assertNotNull(modelservice.findModelByTitle("Testmodel 1-55"));
	}

	@Test
	public void insert500() {
		this.insertQuants(500, 100, 125, 2, 10);
		assertNotNull(modelservice.findModelByTitle("Testmodel 2-55"));
	}

//	@Test
	public void insert1000() {
		this.insertQuants(1000, 125, 175, 3, 10);
	}
	
//	@Test
	public void insert1500() {
		this.insertQuants(1500, 200, 325, 4, 10);
	}
	
}
