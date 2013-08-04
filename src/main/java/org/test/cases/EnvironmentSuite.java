package org.test.cases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.Collection;
import org.components.ModelService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.Edge;
import org.model.Language;
import org.model.Model;
import org.model.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class EnvironmentSuite {
	@Autowired
	ModelService modelservice;
	@Autowired
	Neo4jTemplate template;

	@Test
	public void createFirstModel() {
		Language epk = modelservice.createLanguage("epk",
				"ereignisgesteuerte Prozesskette");
		Model Invoice = modelservice.createModel(epk, "Manage Invoices");
		template.save(Invoice);
		assertEquals(modelservice.findModelById(Invoice.getId()).getTitle(),
				"Manage Invoices");
		assertEquals(modelservice.findModelByTitle("Manage Invoices")
				.getTitle(), Invoice.getTitle());
	}

	@Test
	public void createVertices() {
		Language epk = modelservice.createLanguage("epk",
				"ereignisgesteuerte Prozesskette");
		Model Invoice = modelservice.createModel(epk, "Manage Invoices");
		Vertex n1 = modelservice.createVertex("Invoice incoming");
		Vertex n2 = modelservice.createVertex("Define type of invoice");
		Edge e = modelservice.createEdge(n1, n2, "controlflow");
		Invoice.addEdge(e);
		template.save(Invoice);
	}

	@Test
	public void batchInsertVertices() {
		Language epk = modelservice.createLanguage("epk",
				"ereignisgesteuerte Prozesskette");
		Model Invoice = modelservice.createModel(epk, "Manage Invoices");
		for (int i = 0; i < 100; i++) {
			int z = i + 1;
			Vertex v = modelservice.createVertex("Invoice " + z);
			template.save(v);
		}
		System.out.println("For Controlflow passed");
	}

	@Test
	public void findAllModelsTest() {
		Collection<Model> madeModels = modelservice.makeSomeModels();
		Iterable<Model> foundModels = modelservice.getAllModels();
		int countOfFoundModels = 0;
		for (Model foundModel : foundModels) {
			assertTrue(madeModels.contains(foundModel));
			countOfFoundModels++;
		}
		assertEquals(madeModels.size(), countOfFoundModels);
	}

}
