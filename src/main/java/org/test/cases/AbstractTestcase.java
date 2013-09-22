package org.test.cases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import org.components.ModelService;
import org.model.Edge;
import org.model.Language;
import org.model.Model;
import org.model.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class AbstractTestcase {
	@Autowired
	ModelService modelservice;
	@Autowired
	Neo4jTemplate template;
	
	protected void insertQuants(int size, int vertexCount, int edgeCount,
			int nameAppend, int languageCount, String classifier) {

		/**
		 * create 10 different languages
		 */
		long beginCreationLanguage = System.nanoTime();
		ArrayList<Language> lngs = new ArrayList<Language>();
		long timestamp1 = System.nanoTime();
		for (int i = 0; i < languageCount; i++) {
			String title = "Testsprache " + String.valueOf(i);
			String description = "Beschreibung " + String.valueOf(i);
			lngs.add(modelservice.createLanguage(title, description));
		}
		long endCreationLanguage = System.nanoTime();
		//Persist all created Language Elements
		long beginPersistLanguage = System.nanoTime();
		for( Language langElem: lngs){
			modelservice.saveLanguage(langElem);
		}
		long endPersistLanguage = System.nanoTime();
		
		/**
		 * Create different models and assign them to different language objects
		 */
		long beginCreationModel = System.nanoTime();
		for (int i = 0; i < size; i++) {
			String title = "Testmodel " + String.valueOf(i);
			// get random between 0 and 9
			Random randomGenerator = new Random();
			int fin = randomGenerator.nextInt(9);
			// fetch random Language object out of ArrayList
			Language l = lngs.get(fin);
			Model m = modelservice.createModel(l, title);
			l.addModel(m);
			template.save(l);
			this.populateModel(m, vertexCount, edgeCount);
		}
		long endCreationModel = System.nanoTime();
		try {
			FileWriter fstream = new FileWriter("report/graph-InsertQuants"+classifier+".txt");
			BufferedWriter out = new BufferedWriter(fstream);
			  out.write(getElapsedSeconds(beginCreationLanguage, endCreationLanguage, "Creation of "+String.valueOf(languageCount)+" LanguageElements") 
					  	+ "\n" + 
					  	getElapsedSeconds(beginPersistLanguage, endPersistLanguage, "Persistence of "+String.valueOf(languageCount)+" LanguageObjects")
					  	+"\n"
					  	+getElapsedSeconds(beginCreationModel, endCreationModel, "Creation & Persistence of "+String.valueOf(size)+" ModelObjects")
					  );
			  //Close the output stream
			  out.close();
			  }catch (Exception e){
				  System.err.println("Error: " + e.getMessage());
			  }

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
	
	private String getElapsedSeconds(long begin, long end, String event) {
		long elapsed = (end - begin);
		double calc =  (double) (elapsed / 1000000000.0);
		return event + " took " + String.valueOf(calc) + " Seconds";  
	}
}
