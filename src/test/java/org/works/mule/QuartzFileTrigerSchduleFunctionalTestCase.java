package org.works.mule;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.ftpserver.util.IoUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.client.DefaultLocalMuleClient;
import org.mule.construct.Flow;
import org.mule.tck.junit4.FunctionalTestCase;

public class QuartzFileTrigerSchduleFunctionalTestCase extends FunctionalTestCase {

	String configBase = "E:/Workspace/works-message/src/main/resources/app";
	private String config = configBase + File.pathSeparator + "works-message.xml";

	
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
		
		Map<String, Object> msgProps = new HashMap<String, Object>();
		String BROKER_URL = "vm://localhost";
		try {

			String expectedPayload =IoUtils.readFully(getClass().getResourceAsStream("input.xml")) ;


			String queueName = "queue.request";

			final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

			final Connection connection = connectionFactory.createConnection();

			final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			session.createProducer(session.createQueue(queueName)).send(session.createTextMessage(expectedPayload));

			connection.close();

			MuleClient client = new DefaultLocalMuleClient(muleContext);
			client.dispatch("jms://" + queueName, expectedPayload, msgProps);
			
			MuleMessage response = client.request("jms://" + queueName, 1000);
//			Assert.assertNotNull(response);
//			final String actualPayload = response.getPayloadAsString();
//			Assert.assertNotNull(actualPayload);
//			Assert.assertEquals(expectedPayload, actualPayload);
		} catch (Exception e) {
			e.printStackTrace();
			 fail();
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
			Assert.assertEquals("message", result.getMessage().getPayloadAsString());
		}  catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
