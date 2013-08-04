package org.test;

import org.junit.After;
import org.junit.Test;
import org.relational.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
/**
 * configured for Neo4J
 * 
 * Test case bootstrapping both JavaConfig and XML configuration to validate
 * configuration.
 * 
 * derived from Oliver Gierke
 */
public class ApplicationConfigTest {

	private ConfigurableApplicationContext context;
	
	
	@After
    public void tearDown() throws Exception {
    if (context!=null) context.close();
    }

	@Test
	public void bootstrapAppWithGraphDbFromXml() {
    context = new ClassPathXmlApplicationContext("classpath:/META-INF/application-context.xml");
			assertThat(context, is(notNullValue()));
	}
}


