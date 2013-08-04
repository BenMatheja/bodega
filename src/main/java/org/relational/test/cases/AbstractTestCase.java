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
		 * create different languages
		 */
		ArrayList<Language> lngs = new ArrayList<Language>();
		long timestamp1 = System.nanoTime();
		for (int i = 0; i < languageCount; i++) {
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
			String title = "Testmodel " + String.valueOf(nameAppend) + "-"
					+ String.valueOf(i);
			// get random between 0 and 9
			Random randomGenerator = new Random();
			int fin = randomGenerator.nextInt(size-1);
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
			Edge e = modelservice.createEdge(start, end,
					"Kante: " + start.getCaption() + " to " + end.getCaption());
			modelservice.addEdge(e, model);
		}

	}

}
