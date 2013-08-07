package org.works.message.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotifyMessageListener implements MessageListener {
	private static Logger logger = LoggerFactory
			.getLogger(EmailNotifyMessageListener.class);

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;

		try {
			logger.info("Email content summary:\n{}",
					new Object[] { textMessage.getText() });
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
