package org.works.message.listener;

import java.util.Date;

import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class BackendAccessListener implements
		ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	private long lastFailureEventTime;
	private long timeLastWarningJmsSent;

	// @Autowired
	private transient JmsTemplate jmsTemplate;

	@Override
	public void onApplicationEvent(
			AuthenticationFailureBadCredentialsEvent event) {
		if (lastFailureEventTime > new Date().getTime() - (1000 * 5)) {
			if ((timeLastWarningJmsSent + 1000 * 60 * 5) < new Date().getTime()) {
				timeLastWarningJmsSent = new Date().getTime();
				jmsTemplate.convertAndSend(new Long(event.getTimestamp()));
			}
		}
		lastFailureEventTime = event.getTimestamp();
	}

}
