package org.works.message;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = {
		"classpath*:common/applicationContext-common-registry-locale.xml",
		"classpath:message/applicationContext-message-registry.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class MessageModuleTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	DataSource dataSource;

	@Test
	public void testModuleStartup() {
		System.out.println("Message Module startup success!");

		// Assert.notNull(dataSource);

	}
}
