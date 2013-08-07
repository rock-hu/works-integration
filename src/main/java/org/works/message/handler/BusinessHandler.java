/*
 * $Id: BusinessHandler.java 19191 2010-08-25 21:05:23Z tcarlson $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.works.message.handler;

import org.mule.util.StringMessageUtils;
import org.works.message.bean.ErrorMessage;
import org.works.message.bean.HandlerException;
import org.works.message.bean.LocaleMessage;
import org.works.message.exception.BusinessException;

/**
 * <code>BusinessHandler</code>
 */
public class BusinessHandler extends AbstractExceptionHandler {

	public BusinessHandler() {
		super();
		registerException(BusinessException.class);
	}

	protected void processException(ErrorMessage message, Throwable t)
			throws HandlerException {
		String msg = LocaleMessage.businessHandlerMessage();
		System.out.println(StringMessageUtils.getBoilerPlate(msg));
	}
}
