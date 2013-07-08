package org.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ApplicationConfigTest.class,
	EnvironmentSuite.class, 
	UseCaseSuite.class
	})
public class TestSuite {
	
	@BeforeClass
	public static void setUpClass(){
		System.out.println("Master setup");
	}

}
