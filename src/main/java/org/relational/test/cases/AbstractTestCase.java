package org.relational.test.cases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.relational.components.ModelService;
import org.relational.model.Edge;
import org.relational.model.Language;
import org.relational.model.Model;
import org.relational.model.Vertex;
import org.relational.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import ch.qos.logback.core.pattern.parser.Node;

public class AbstractTestCase {
	@Autowired
	ModelService modelservice;
	@Autowired
	LanguageRepository languageRepository;

	protected void insertQuants(int size, int vertexCount, int edgeCount,
			int nameAppend, int languageCount) {
		/**
		 * create different languageElements
		 */
		long beginCreationLanguage = System.nanoTime();
		ArrayList<Language> lngs = new ArrayList<Language>();
		for (int i = 0; i < languageCount; i++) {
			String title = "Testsprache " + String.valueOf(i);
			String description = "Beschreibung " + String.valueOf(i);
			lngs.add(modelservice.createLanguage(title, description));
		}
		long endCreationLanguage = System.nanoTime();
		
		//Persist all created Language Elements
		long beginPersistLanguage = System.nanoTime();
		System.out.println("REACHED LANGUAGE PERSISTENCE");
		for( Language langElem: lngs){
			modelservice.saveLanguage(langElem);
		}
		long endPersistLanguage = System.nanoTime();
	
		/**
		 * Create different models and assign them to different language objects
		 */
		long beginCreationModel = System.nanoTime();
		for (int i = 0; i < size; i++) {
			String title = "Testmodel " + String.valueOf(nameAppend) + "-" + String.valueOf(i);
			Random randomGenerator = new Random();
			int fin = randomGenerator.nextInt(languageCount);
			// fetch random Language object out of ArrayList
			Language l = lngs.get(fin);
			Model m = modelservice.createModel(l, title);
			l.addModel(m);
			modelservice.saveModel(m);
			modelservice.saveLanguage(l);
			this.populateModel(m, vertexCount, edgeCount);
		}
		long endCreationModel = System.nanoTime();
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getElapsedSeconds(beginCreationLanguage, endCreationLanguage, "Creation of LanguageElements");
		getElapsedSeconds(beginPersistLanguage, endPersistLanguage, "Persistence of LanguageObjects");
		getElapsedSeconds(beginCreationModel, endCreationModel, "Creation & Persistence of ModelObjects");

	}

	private void populateModel(Model model, int vertexCount, int edgeCount) {
		/**
		 * Generate sample Vertices
		 */
		Random randomGenerator = new Random();
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
			Edge e = modelservice.createEdge(start, end,
					"Kante: " + start.getCaption() + " to " + end.getCaption());
			modelservice.addEdge(e, model);
		}
	}
	
	private void getElapsedSeconds(long begin, long end, String event) {
		long elapsed = (end - begin);
		double calc =  (double) (elapsed / 1000000000.0);
		System.out.println( event + " took " + String.valueOf(calc) + " Seconds");  
	}
	

}
