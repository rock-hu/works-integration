package org.works.message.transformer;

import org.springframework.batch.core.JobExecution;
import org.springframework.integration.Message;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.support.MessageBuilder;

public class ExecutionsToMailTransformer {

	private String to;
	private String from;

	@Transformer
	public Message<String> transformExecutionsToMail(JobExecution jobExecution) {
		String result = "Execution has ended "
				+ jobExecution.getStatus().toString();
		return MessageBuilder.withPayload(result).setHeader(MailHeaders.TO, to)
				.setHeader(MailHeaders.FROM, from).build();
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
