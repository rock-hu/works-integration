/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.works.message.transformer;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transformer.types.DataTypeFactory;
import org.mule.util.StringUtils;
import org.works.message.bean.Book;
import org.works.message.factory.BookstoreAdminMessages;

/**
 * Transforms a Map of HttpRequest parameters into a Book object. The request
 * parameters are always strings (they come from the HTML form), so we need to
 * parse and convert them to their appropriate types.
 */
public class HttpRequestToBook extends AbstractMessageTransformer {
	public HttpRequestToBook() {
		super();
		registerSourceType(DataTypeFactory.OBJECT);
		setReturnDataType(DataTypeFactory.create(Book.class));
	}

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		String author = message.getInboundProperty("author");
		String title = message.getInboundProperty("title");
		String price = message.getInboundProperty("price");

		if (StringUtils.isBlank(author)) {
			throw new TransformerException(
					BookstoreAdminMessages.missingAuthor(), this);
		}
		if (StringUtils.isBlank(title)) {
			throw new TransformerException(
					BookstoreAdminMessages.missingTitle(), this);
		}
		if (StringUtils.isBlank(price)) {
			throw new TransformerException(
					BookstoreAdminMessages.missingPrice(), this);
		}

		return new Book(author, title, Double.parseDouble(price));
	}
}
