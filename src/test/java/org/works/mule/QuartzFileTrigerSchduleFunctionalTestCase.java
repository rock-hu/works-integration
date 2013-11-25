package org.works.mule;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.client.DefaultLocalMuleClient;
import org.mule.construct.Flow;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.util.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.works.batch.domain.Gender;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:message/applicationContext-message-registry.xml" })
public class QuartzFileTrigerSchduleFunctionalTestCase extends FunctionalTestCase implements ApplicationContextAware {

	@Autowired(required = false)
	@Qualifier(value = "connectionFactory")
	ConnectionFactory connectionFactory;

	@Autowired(required = false)
	@Qualifier(value = "requestQueue")
	Queue requestQueue;

	protected ApplicationContext applicationContext;

	String configBase = "E:/Workspace/works-message/src/main/resources/mule/app";
	private String config = configBase + File.pathSeparator + "works-message.xml";

	int timeout = 600;

	@Override
	protected void doTearDown() throws Exception {
		if (muleContext != null) {
			muleContext.dispose();
			System.gc();
		}

	}

	@Override
	protected void doSetUp() throws Exception {
		String[] beans = applicationContext.getBeanDefinitionNames();
		System.out.println("applicationContext beans:");
		for (String bean : beans) {
			System.out.println(bean);
		}
	}

	@Override
	protected int getTimeoutSystemProperty() {
		return timeout;
	}

	@Override
	protected String getConfigResources() {
		return configBase + File.separator + "works-message.xml";
	}

	@Test
	public void testTestCase() throws Exception {

		Map<String, String> artifactCaches = new ConcurrentHashMap();

		StringBuffer artifactRegisters = new StringBuffer("");

		String slash = "/";

		String ext = ".jar";
		// /D:/Server/mule-standalone-3.4.0/lib/opt/ant-1.7.0.jar
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		// Get the URLs
		URL[] urls = ((URLClassLoader) sysClassLoader).getURLs();
		System.out.println("################################################################");

		for (URL url : urls) {

			if (url.getPath().endsWith(ext)) {

			}

			System.out.println(url.getFile());
		}

		System.out.println("################################################################");
	}

	@Test
	public void testFile() {
		MuleClient client = new DefaultLocalMuleClient(muleContext);
	}

	@Test
	public void testMail() {

	}

	@Test
	public void testQuartz() {

	}

	@Test
	public void testJms() {

		System.out.println("Begin testJms");
		System.out.println(Gender.valueOf("M"));

		Connection connection = null;
		Session session = null;
		Map<String, Object> msgProps = new HashMap<String, Object>();
		try {

			String expectedPayload = IOUtils.toString(getClass().getResourceAsStream("customer-input.xml"));
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			session.createProducer(requestQueue).send(session.createTextMessage(expectedPayload));

			MuleClient client = new DefaultLocalMuleClient(muleContext);
			client.dispatch("jms://" + requestQueue.getQueueName(), expectedPayload, msgProps);

			MuleMessage response = client.request("jms://" + requestQueue.getQueueName(), 1000);

			System.out.println(response);

			// Assert.assertNotNull(response);
			// final String actualPayload = response.getPayloadAsString();
			// Assert.assertNotNull(actualPayload);
			// Assert.assertEquals(expectedPayload, actualPayload);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

		System.out.println("End testJms");
	}

	@Test
	public void testHttp() {

	}

	@Test
	public void testCxf() {

	}

	@Test
	public void testFtp() {
		try {
			MuleClient client = new DefaultLocalMuleClient(muleContext);
			Flow flow = (Flow) getFlowConstruct("ftpDownloadFlow");

			File downloadDir = new File("E:/Workspace/works-integration/data/ftp/out");

			MuleEvent event = getTestEvent(downloadDir, flow);
			MuleEvent result = flow.process(event);
			// Assert.assertEquals("message",
			// result.getMessage().getPayloadAsString());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
