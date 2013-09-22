package org.test.cases;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import java.util.Set;

import org.components.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.Model;
import org.model.Edge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
// persist it to file
public class QueryTest extends AbstractTestcase {
@Autowired
ModelService modelService;
	@Before
	public void setUp() {
		this.insertQuants(5000, 50, 25, 1, 5, "QueryInsert"); // models,
																// Vertex,
																// Edges, name,
																// Languages
	}

	@Test
	public void GetModelbyName() {
		int modelCount = 5000; 
		Random randomGenerator = new Random();
		int fin = randomGenerator.nextInt(modelCount);
		int lin = randomGenerator.nextInt(modelCount);
		int xin = randomGenerator.nextInt(modelCount);
		int din = randomGenerator.nextInt(modelCount);
		int hin = randomGenerator.nextInt(modelCount);
		int rin = randomGenerator.nextInt(modelCount);
		
		long startFetchModel1 = System.nanoTime();
		Model Found = modelService.findModelByTitle("Testmodel 1-"+String.valueOf(fin));
		Set<Edge>	  Edges = Found.getAllEdges();
		long endFetchModel1 = System.nanoTime();
		Model Found2 = modelService.findModelByTitle("Testmodel 1-"+String.valueOf(lin));
		Set<Edge>	  Edges1 = Found2.getAllEdges();
		long endFetchModel2 = System.nanoTime();
		Model Found3 = modelservice.findModelByTitle("Testmodel 1-"+String.valueOf(xin));
		Set<Edge>	  Edges2 = Found3.getAllEdges();
		long endFetchModel3 = System.nanoTime();
		Model Found4 = modelservice.findModelByTitle("Testmodel 1-"+String.valueOf(din));
		Set<Edge>	  Edges3 = Found4.getAllEdges();
		long endFetchModel4 = System.nanoTime();
		Model Found5 = modelservice.findModelByTitle("Testmodel 1-"+String.valueOf(hin));
		Set<Edge>	  Edges4 = Found5.getAllEdges();
		long endFetchModel5 = System.nanoTime();
		Model Found6 = modelservice.findModelByTitle("Testmodel 1-"+String.valueOf(rin));
		Set<Edge>	  Edges5 = Found6.getAllEdges();
		long endFetchModel6 = System.nanoTime();
		try {
			FileWriter fstream = new FileWriter("report/graph-getModelsByName.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(getElapsedSeconds(startFetchModel1, endFetchModel1,"Find Model 1 by Title")+ "\n"
					+ getElapsedSeconds(endFetchModel1, endFetchModel2,	"Find Model 2 by Title") + "\n"
					+ getElapsedSeconds(endFetchModel2, endFetchModel3, "Find Model 3 by Title") +"\n"
					+ getElapsedSeconds(endFetchModel3, endFetchModel4, "Find Model 4 by Title")+ "\n"
					+ getElapsedSeconds(endFetchModel4, endFetchModel5, "Find Model 5 by Title")+ "\n"
					+ getElapsedSeconds(endFetchModel5, endFetchModel6, "Find Model 6 by Title")+ "\n"
					);
			// Close the output stream
			out.close();
		} catch (Exception e) {
			// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		assertNotNull(Edges);
		assertNotNull(Edges1);
		assertNotNull(Edges2);
		assertNotNull(Edges2);
		assertNotNull(Edges3);
		assertNotNull(Edges4);
		assertNotNull(Edges5);
	}

	private String getElapsedSeconds(long begin, long end, String event) {
		long elapsed = (end - begin);
		double calc = (double) (elapsed / 1000000000.0);
		return event + " took " + String.valueOf(calc) + " Seconds";
	}

}
