package org.relational.test.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Set;

import javax.sound.midi.VoiceStatus;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.Each;
import org.junit.runner.RunWith;
import org.relational.components.ModelService;
import org.relational.model.Edge;
import org.relational.model.Model;
import org.relational.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.xpath.internal.FoundIndex;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context-relational.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)// persist it to file
public class QueryTest extends AbstractTestCase {
	@Autowired
	ModelService modelservice;
	
	int models = 10;
	
	@Before
	public void setUp() {
		this.insertQuants(models, 1000, 1050, 1, 1); //models, Vertex, Edges, name, Languages 
	}
	
	@Test
	public void GetModelbyName() {
		long startFetchModel1 = System.nanoTime();
		Model Found = 		modelservice.findModelByTitle("Testmodel 1-0");
		long endFetchModel1 = System.nanoTime();
		Model Found2 = modelservice.findModelByTitle("Testmodel 1-5");
		long endFetchModel2 = System.nanoTime();
		try {
			FileWriter fstream = new FileWriter("report/getModelsByName.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			  out.write(getElapsedSeconds(startFetchModel1, endFetchModel1, "Find Model 1 by Title") 
					  	+ "\n" + 
					  	getElapsedSeconds(endFetchModel1, endFetchModel2, "Find Model 2 by Title")
					  	+"\n");
			  //Close the output stream
			  out.close();
			  }catch (Exception e){
				  //Catch exception if any
				  System.err.println("Error: " + e.getMessage());
			  }
		assertNotNull(Found);
		assertNotNull(Found2);
	}
	
	@Test
	public void GetAllModels() {
		long startFetchModels = System.nanoTime();
		Iterable<Model> foundModels = modelservice.getAllModels();
		long endFetchModels = System.nanoTime();
		int countOfFoundModels = 0;
		for (Model foundModel : foundModels) {
			assertNotNull(foundModel);
			countOfFoundModels++;
		}
		assertNotNull(countOfFoundModels);
		assertEquals(models, countOfFoundModels);
		try {
			FileWriter fstream = new FileWriter("report/getAllModels.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			  out.write(getElapsedSeconds(startFetchModels, endFetchModels, "Fetch of All created Models") 
					  	+ "\n");
			  out.close();
			  }catch (Exception e){
				    System.err.println("Error: " + e.getMessage());
			  }
	}
	@Test
	public void loadEdgesForSpecificModel() {
		Model model = modelservice.findModelByTitle("Testmodel 1-0");
		long startFetchEdges = System.nanoTime();
		Set<Edge> edges = model.getAllEdges();
		long endFetchEdges = System.nanoTime();
		assertNotNull(edges);
		
		try {
			FileWriter fstream = new FileWriter("report/loadEgesFor1Model.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			  out.write(getElapsedSeconds(startFetchEdges, endFetchEdges, "Fetch of All Edges for Models") 
					  	+ "\n");
			for (Edge edge : edges) {
					out.write(edge.toString()+"\n");
				}
			  out.close();
			  }catch (Exception e){
				    System.err.println("Error: " + e.getMessage());
			  }	
	}
	
	private String getElapsedSeconds(long begin, long end, String event) {
		long elapsed = (end - begin);
		double calc =  (double) (elapsed / 1000000000.0);
		return event + " took " + String.valueOf(calc) + " Seconds";  
	}
	
}
 