package org.relational.test;

//Domain objects
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
public class UseCaseSuite {
	@Autowired
	ModelService modelservice;
	@Autowired
	LanguageRepository languageRepository;

	@Before
	public void setUp() {

	}

	@Test
	public void insert100() {
		this.insertQuants(100, 50, 75); //models, vertices, edges
	}

	@Test
	public void insert500() {
		this.insertQuants(500, 100, 125);
	}

//	@Test
//	public void insert1000() {
//		this.insertQuants(1000, 125, 175);
//	}
	
//	@Test
//	public void insert1500() {
//		this.insertQuants(1500, 200, 325 );
//	}
	

	private void insertQuants(int size, int vertexCount, int edgeCount) {

		/**
		 * create 10 different languages
		 */
		ArrayList<Language> lngs = new ArrayList<Language>();
		long timestamp1 = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			String title = "Testsprache " + String.valueOf(i);
			String description = "Beschreibung " + String.valueOf(i);
			lngs.add(modelservice.createLanguage(title, description));
		}
		long timestamp2 = System.nanoTime();
		long elapsed1 = (timestamp2 - timestamp1);
		double seconds1 = (double) elapsed1 / 1000000000.0;
		long timestamp3 = System.nanoTime();
		/**
		 * Create different models and assign them to different language objects
		 */
		for (int i = 0; i < size; i++) {
			String title = "Testmodel " + String.valueOf(i);
			// get random between 0 and 9
			Random randomGenerator = new Random();
			int fin = randomGenerator.nextInt(9);
			// fetch random Language object out of ArrayList
			Language l = lngs.get(fin);
			Model m = modelservice.createModel(l, title);
			l.addModel(m);
			languageRepository.save(l);
			this.populateModel(m, vertexCount, edgeCount);
		}

		long timestamp4 = System.nanoTime();
		long elapsed2 = (timestamp4 - timestamp3);
		double seconds2 = (double) elapsed2 / 1000000000.0;
		System.out.println("Language Creation of 10 objects took "
				+ String.valueOf(seconds1) + " seconds");
		System.out.println("Storage of " + String.valueOf(size)
				+ " Model objects took " + String.valueOf(seconds2)
				+ " seconds");
	}

	private void populateModel(Model model, int vertexCount, int edgeCount) {
		Random randomGenerator = new Random();
		/**
		 * Generate sample Vertices
		 */
		ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
		
		for (int i = 0; i < vertexCount; i++) {
			Vertices.add(modelservice.createVertex("Knoten " + i));
		}
		/**
		 * Connect those samples
		 */
		for (int j = 0; j < edgeCount; j++) {
			int fin;
			int lin;
			do {
				fin = randomGenerator.nextInt(50);
				lin = randomGenerator.nextInt(50);
			} while (fin == lin); // prevent getting similar numbers

			Vertex start = Vertices.get(fin);
			Vertex end = Vertices.get(lin);
			Edge e = modelservice.createEdge(start, end, "Kante: "+start.getCaption()+" to "+end.getCaption());
			modelservice.addEdge(e, model);
		}

	}

}
