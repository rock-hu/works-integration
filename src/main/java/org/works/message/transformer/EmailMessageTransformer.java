package org.works.message.transformer;

import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.springframework.core.convert.converter.Converter;

public class EmailMessageTransformer extends AbstractTransformer {

	private Converter converter;

	public EmailMessageTransformer() {
		registerSourceType(MimeBodyPart.class);
		registerSourceType(Message.class);
		registerSourceType(String.class);
		setReturnClass(String.class);
	}

	@Override
	protected Object doTransform(Object src, String encoding)
			throws TransformerException {
		return converter.convert(src);
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}

}
