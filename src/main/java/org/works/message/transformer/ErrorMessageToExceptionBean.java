/*
 * $Id: ErrorMessageToExceptionBean.java 19250 2010-08-30 16:53:14Z dirk.olmes $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.works.message.transformer;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;
import org.works.message.bean.ErrorMessage;

/**
 * The <code>ErrorMessageToExceptionBean</code> transformer returns the
 * exception bean encapsulated by the ErrorMessage message payload.
 */
public class ErrorMessageToExceptionBean extends AbstractTransformer {

	public ErrorMessageToExceptionBean() {
		registerSourceType(DataTypeFactory.create(ErrorMessage.class));
	}

	@Override
	public Object doTransform(Object src, String encoding)
			throws TransformerException {
		return ((ErrorMessage) src).getException();
	}

}
