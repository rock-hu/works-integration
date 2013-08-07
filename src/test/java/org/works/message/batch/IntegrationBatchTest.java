package org.works.message.batch;

import static org.junit.Assert.fail;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {
		"classpath:common/applicationContext-common-registry-locale.xml",
		"classpath:batch/applicationContext-batch-registry.xml",
		"classpath:message/integration/batch/integration-batch.xml" })
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
//@Transactional
public class IntegrationBatchTest extends AbstractJUnit4SpringContextTests {

	private final Logger logger = LoggerFactory
			.getLogger(IntegrationBatchTest.class);

	@Autowired
	@Qualifier(value = "dataSource")
	DataSource dataSource;

	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	@Qualifier(value = "importPayments")
	Job importPayments;
	JobParameters jobParameters;

	@Before
	public void prepareContext() {
//		String[] configs = new String[] {
//				"classpath:common/applicationContext-common-registry-locale.xml",
//				"classpath:batch/applicationContext-batch-registry.xml",
//				"classpath:message/integration/batch/integration-batch.xml" };
//
//		applicationContext = new ClassPathXmlApplicationContext(configs);
//		setApplicationContext(applicationContext);
	}

	@Test
	public void testIntegrationBatch() {

		JobParametersBuilder builder = new JobParametersBuilder();

		builder.addString("input.file.name",
				"/develop/workspace/works-integration/works-message/src/test/resources/data/paymentImport/payment.input");
		jobParameters = builder.toJobParameters();
		try {
			jobLauncher.run(importPayments, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
			fail();
		} catch (JobRestartException e) {
			e.printStackTrace();
			fail();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
			fail();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testPrintClassPathJars() {

		// String pathPrefix = "/develop/repository/";
		//
		// Set<String> jars = new HashSet<String>();
		//
		// ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		//
		// // Get the URLs
		// URL[] urls = ((URLClassLoader) sysClassLoader).getURLs();
		//
		// for (int i = 0; i < urls.length; i++) {
		//
		// String fullPath = urls[i].getFile();
		// // StringUtils.deleteAny(fullPath, pathPrefix)
		// jars.add(fullPath);
		//
		// }
		// List<String> depencyJars = new ArrayList<String>(jars);
		// Collections.sort(depencyJars);
		//
		// for (String jar : depencyJars) {
		// // logger.info(jar);
		// System.out.println(jar);
		// }

	}
}
