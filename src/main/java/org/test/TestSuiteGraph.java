package org.test;

import org.junit.runner.RunWith;
import org.relational.test.cases.InsertTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.test.cases.ApplicationConfigTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ApplicationConfigTest.class,
	InsertTest.class
	})
public class TestSuiteGraph {
	
	@BeforeClass
	public static void setUpClass(){
		System.out.println("Master setup");
	}

}
