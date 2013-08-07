/*
 * $Id: DefaultHandler.java 19191 2010-08-25 21:05:23Z tcarlson $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.works.message.handler;

import org.mule.util.StringMessageUtils;
import org.works.message.bean.ErrorManager;
import org.works.message.bean.ErrorMessage;
import org.works.message.bean.HandlerException;
import org.works.message.bean.LocaleMessage;

/**
 * <code>DefaultHandler</code> TODO (document class)
 * 
 */
public class DefaultHandler extends AbstractExceptionHandler {

	public DefaultHandler() {
		super();
		registerException(Throwable.class);
	}

	public void processException(ErrorMessage message, Throwable t)
			throws HandlerException {
		String msg = LocaleMessage.defaultHandlerMessage();
		System.out.println(StringMessageUtils.getBoilerPlate(msg));
	}

	@Override
	public void setErrorManager(ErrorManager errorManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(ErrorMessage message) throws HandlerException {
		// TODO Auto-generated method stub

	}

}
