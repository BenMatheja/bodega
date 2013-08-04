package org.relational.test.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import javax.sound.midi.VoiceStatus;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.Each;
import org.junit.runner.RunWith;
import org.relational.components.ModelService;
import org.relational.model.Model;
import org.relational.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context-relational.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)// persist it to file
public class QueryTest extends AbstractTestCase {
	@Autowired
	ModelService modelservice;

	@Before
	public void setUp() {
		this.insertQuants(2, 100, 150, 1, 1); //models, Vertex, Edges, name, Languages 
	}
	
	@Test
	public void GetModelbyName() {
		assertNotNull(modelservice.findModelByTitle("Testmodel 1-0"));
		assertNotNull(modelservice.findModelByTitle("Testmodel 1-1"));
	}
	
	@Test
	public void GetAllModels() {
		Iterable<Model> foundModels = modelservice.getAllModels();
		int countOfFoundModels = 0;
		for (Model foundModel : foundModels) {
			assertNotNull(foundModel);
			countOfFoundModels++;
		}
		assertNotNull(countOfFoundModels);
		assertEquals(2, countOfFoundModels);
	}
	
}
