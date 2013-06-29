package org.test;

import static org.junit.Assert.assertEquals;

import org.components.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.Edge;
import org.model.Language;
import org.model.Model;
import org.model.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ModelRepositoryTest {
	@Autowired
	private ModelService modelservice;
	@Autowired
	public Neo4jTemplate template;
	
	@Test
	public void createFirstModel(){
		Language epk = modelservice.createLanguage("EPK", "Ereignisgesteuerte Prozesskette");
		Vertex n1 = modelservice.createVertex("Invoice incoming");
		Vertex n2 = modelservice.createVertex("Define type of invoice");
	
		
		Model invoiceManagement = new Model(epk,"Manage Invoices" );
		template.save(invoiceManagement);
		assertEquals(modelservice.findModelByTitle("Manage Invoices"), invoiceManagement.getTitle());
		
		
		
		
		
	}

}
