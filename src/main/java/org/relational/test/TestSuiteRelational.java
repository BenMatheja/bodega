package org.relational.test;

import org.junit.runner.RunWith;
import org.relational.test.cases.ApplicationConfigTest;
import org.relational.test.cases.InsertTest;
import org.relational.test.cases.QueryTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ApplicationConfigTest.class, 
	InsertTest.class,
	QueryTest.class
	})
/**
 * ApplicationConfigTest : Bootstrap App from XML config
 * UseCaseSuite : Insert Model with configurable amount of interconnected Vertices with Edges into the Database
 * @author ben
 *
 */
public class TestSuiteRelational {
	
	@BeforeClass
	public static void setUpClass(){
		System.out.println("Master setup");
	}

}
