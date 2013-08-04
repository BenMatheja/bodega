/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.relational.test.cases;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.relational.repositories.ModelRepository;
/**
 * configured for HSQL
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
		if (context != null) context.close();
	}

	@Test
	public void bootstrapAppWithRelationalFromXml() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"META-INF/application-context-relational.xml");
		assertThat(context, is(notNullValue()));
		assertThat(context.getBean(ModelRepository.class), is(notNullValue()));
	}
}
