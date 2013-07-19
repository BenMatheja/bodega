package org.test.relational;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.components.jpa.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.relational.Language;
import org.model.relational.Model;
import org.repositories.relational.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context-relational.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCaseSuite {
	@Autowired
	ModelService modelservice;
	@Autowired
	LanguageRepository languageRepository;
	
	@Before
	public void setUp(){	
	}
	
	@Test
	public void insert100(){
		this.insertQuants(100);
	}
	
//	@Test
//	public void insert500(){
//		this.insertQuants(500);
//	}
//	
//	@Test
//	public void insert1000(){
//		this.insertQuants(1000);
//	}
//	@Test
	public void testModelCreation(){
		Collection<Model> models = modelservice.makeSomeModels();
//		for(Model model : models){
//			this.populateModel(model);
//		}
	}
	
	private void insertQuants(int size){
		
		/**
		 * create 10 different languages
		 */
		ArrayList<Language> lngs = new ArrayList<Language>();
		long timestamp1 = System.nanoTime();
		for(int i=0; i<10; i++){
			String title = "Testsprache " + String.valueOf(i);
			String description = "Beschreibung " +String.valueOf(i); 
			lngs.add(modelservice.createLanguage(title,description));
		}
		long timestamp2 = System.nanoTime();
		long elapsed1 = (timestamp2 - timestamp1);
		double seconds1 = (double) elapsed1 /1000000000.0;
		long timestamp3 = System.nanoTime();
		/**
		 * Create different models and assign them to different
		 * language objects
		 */
		for(int i=0; i<size; i++){
			String title = "Testmodel " + String.valueOf(i);
			//get random between 0 and 9
			Random randomGenerator = new Random();
			int fin = randomGenerator.nextInt(9);
			//fetch random Language object out of ArrayList
			Language l = lngs.get(fin);
			Model m = modelservice.createModel(l, title);
			l.addModel(m);
			languageRepository.save(l);
		}
		
		long timestamp4 = System.nanoTime();
		long elapsed2 = (timestamp4 - timestamp3);
		double seconds2 = (double) elapsed2 /1000000000.0;
		System.out.println("Language Creation of 10 objects took "+ String.valueOf(seconds1) + " seconds");
		System.out.println("Storage of "+ String.valueOf(size) +" Model objects took "+ String.valueOf(seconds2) + " seconds");
	}
	
//	private void populateModel(Model model){
//		Random randomGenerator = new Random();
//		/**
//		 * Generate sample Vertices
//		 */
//		ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
//		for(int i=0;i<50;i++){
//			Vertices.add(modelservice.createVertex("Kante "+i, model));
//		}
//		/**
//		 * Connect those samples
//		 */
//		for(int j=0;j<25;j++){
//			int fin;
//			int lin;
//			do{ 
//				fin = randomGenerator.nextInt(50); 
//				lin = randomGenerator.nextInt(50);
//			}
//			while(fin==lin); //prevent getting similar numbers
//	
//			Vertex start = Vertices.get(fin);
//			Vertex end = Vertices.get(lin);
//			
//			start.connectWith(end,"controlflow "+j);
//			template.save(start);
//		}
//		
//		
//		
//		
//		
//		
//	}

}
