package org.works.message.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class BaseJmsQueueListener implements MessageListener {

	private static final Log LOG = LogFactory
			.getLog(BaseJmsQueueListener.class);

	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public void onMessage(Message message) {

		LOG.debug("Begin process message...");

		Message msg = jmsTemplate.receive();

		LOG.debug("End process message...");

	}

}
