package org.works.message.converter;

import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class MimeBodyPartConverter implements Converter<MimeBodyPart, String> {

	private static final Logger logger = LoggerFactory
			.getLogger(MimeBodyPartConverter.class);

	@Override
	public String convert(MimeBodyPart source) {

		String content = "";

		try {
			Object mailContent = source.getContent();

			if (mailContent instanceof String) {
				content = (String) mailContent;
			} else if (mailContent instanceof MimeMultipart) {
				MimeMultipart mime = (MimeMultipart) mailContent;
				if (mime != null) {
					BodyPart body = mime.getBodyPart(0);
					if (body != null) {
						content = (String) body.getContent();
					}
				}
			} else {
				logger.error("There is unknow part from the MimebodyPart.getContent");
			}

		} catch (IOException e) {
			logger.error("IOException in convert email.", e);
		} catch (MessagingException e) {
			logger.error("MessagingException in convert email.", e);
		}

		return content;
	}

}
