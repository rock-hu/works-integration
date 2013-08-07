package org.works.message.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:message/applicationContext-message-registry.xml" })
public class JmsStartupTest extends AbstractJUnit4SpringContextTests {

	private final Logger logger = LoggerFactory.getLogger(JmsStartupTest.class);
	@Autowired
	@Qualifier(value = "connectionFactory")
	ConnectionFactory connectionFactory;
	@Autowired
	@Qualifier(value = "requestQueue")
	Queue requestQueue;
	@Autowired
	@Qualifier(value = "replyQueue")
	Queue replyQueue;
	@Autowired
	@Qualifier(value = "notifyTopic")
	Topic notifyTopic;
	@Autowired
	@Qualifier(value = "jmsTemplate")
	JmsTemplate jmsTemplate;

	@Test
	public void testStartup() throws JMSException {
		// Send
		jmsTemplate.send(requestQueue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();

				message.setText("\n#################################################\n"
						+ "Sending Text Message to queue."
						+ "\n#################################################");

				return message;
			}

		});
		// Receive
		Message msg = jmsTemplate.receive(requestQueue);

		if (msg instanceof TextMessage) {
			TextMessage textMsg = (TextMessage) msg;
			logger.debug(textMsg.getText());
		}

		// Sub/Pub

		jmsTemplate.send(notifyTopic, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();

				message.setText("\n#################################################\n"
						+ "Sending Text Message to topic."
						+ "\n#################################################");

				return message;
			}

		});

	}
}
