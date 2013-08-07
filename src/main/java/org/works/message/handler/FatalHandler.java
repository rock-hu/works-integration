/*
 * $Id: FatalHandler.java 19191 2010-08-25 21:05:23Z tcarlson $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.works.message.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.lifecycle.FatalException;
import org.mule.util.StringMessageUtils;
import org.works.message.bean.ErrorMessage;
import org.works.message.bean.HandlerException;
import org.works.message.bean.LocaleMessage;

/**
 * <code>FatalBehaviour</code> TODO (document class)
 */
public class FatalHandler extends DefaultHandler {
	/** logger used by this class */
	private static final Log logger = LogFactory.getLog(FatalHandler.class);

	public FatalHandler() {
		super();
		registerException(FatalException.class);
	}

	public void processException(ErrorMessage message, Throwable t)
			throws HandlerException {
		String msg = LocaleMessage.fatalHandlerMessage();
		System.out.println(StringMessageUtils.getBoilerPlate(msg));
		logger.fatal(LocaleMessage.fatalHandlerException(t), t);
	}
}
