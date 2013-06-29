package org.test;

import static org.junit.Assert.assertEquals;

import org.components.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.Edge;
import org.model.Language;
import org.model.Model;
import org.model.Vertex;
import org.repositories.ModelRepository;
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
		Language epk = modelservice.createLanguage("epk","ereignisgesteuerte Prozesskette");
		Model Invoice = modelservice.createModel(epk, "Manage Invoices");
		template.save(Invoice);
		assertEquals(modelservice.findModelById(Invoice.getId()).getTitle(),"Manage Invoices");
		Vertex n1 = modelservice.createVertex("Invoice incoming", Invoice);
		template.save(n1);
		Vertex n2 = modelservice.createVertex("Define type of invoice", Invoice);
		template.save(n2);
		Edge e = n1.connectWith(n2,"Controlflow");
		template.save(e);
		template.save(n1);

		
//		Model invoiceManagement = new Model();
//		invoiceManagement.setTitle("InvoiceManagment");
//		invoiceManagement.setLanguage(epk);
//		template.save(invoiceManagement);
		assertEquals(modelservice.findModelByTitle("Manage Invoices").getTitle(), Invoice.getTitle());
		
		
		
		
		
	}

}
