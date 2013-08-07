package org.works.message.listener;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;

public class JmsSecurityTopicListener extends BaseJmsQueueListener {
	@Autowired
	private transient MailSender mailSender;

	@Override
	public void onMessage(Message message) {

	}

}
