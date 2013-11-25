package org.works.message.component;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchInvokeComponent implements Callable {

	private Logger logger = LoggerFactory.getLogger(BatchInvokeComponent.class);

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

		logger.info("invoke spring batch here.");

		return eventContext.getMessage();
	}

}
