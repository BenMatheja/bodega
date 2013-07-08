package org.test;

import java.util.ArrayList;

import org.components.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.Language;
import org.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:/META-INF/application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UseCaseSuite {
	@Autowired
	ModelService modelservice;
	@Autowired
	Neo4jTemplate template;
	
	@Before
	public void setUp(){
		
		
		
	}
	
	@Test
	public void insertQuants(){
		int size =500;
		/**
		 * create 10 different languages
		 */
		ArrayList<Language> lngs = new ArrayList<Language>();
		long timestamp1 = System.nanoTime();
		for(int i=0; i<10; i++){
			String title = "Testsprache " + String.valueOf(i);
			String description = "Beschreibung " +String.valueOf(i) + " test"; 
			lngs.add(modelservice.createLanguage(title,description));
		}
		long timestamp2 = System.nanoTime();
		double elapsed1 = (timestamp2 - timestamp1)/(10^9);
		ArrayList<Model> models = new ArrayList<Model>();
		long timestamp3 = System.nanoTime();
		for(int i=0; i<size; i++){
			String title = "Testmodel " + String.valueOf(i);
			int rand = (int)Math.round(Math.random()*9);
			models.add(modelservice.createModel(lngs.get(rand), title));
		}
		long timestamp4 = System.nanoTime();
		double elapsed2 = (timestamp4 - timestamp3) /(10^9);
		System.out.println("Language Creation of 10 objects took "+ String.valueOf(elapsed1) + " ms");
		System.out.println("Storage of "+ String.valueOf(size) +" Model objects took "+ String.valueOf(elapsed2) + " ms");
	}
	

}
