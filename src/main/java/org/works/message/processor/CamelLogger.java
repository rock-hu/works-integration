package org.works.message.processor;

import java.util.Map;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelLogger implements Processor {

	private Logger logger = LoggerFactory.getLogger(CamelLogger.class);

	@Override
	public void process(Exchange exchange) throws Exception {

		Map<String, Object> headers = exchange.getIn().getHeaders();
		logger.debug("begin print camel in headers information:");

		Set<String> headerKeys = headers.keySet();
		for (String key : headerKeys) {
			logger.debug("header: " + key + ", value object: "
					+ headers.get(key));
		}

		logger.debug("end print camel in headers information:");

		headers = exchange.getOut().getHeaders();
		logger.debug("begin print camel out headers information:");

		headerKeys = headers.keySet();
		for (String key : headerKeys) {
			logger.debug("header: " + key + ", value object: "
					+ headers.get(key));
		}
		logger.debug("end print camel out headers information:");

		Exception ex = exchange.getException();
		if (ex != null) {
			logger.debug("begin print camel exception information:");
			logger.debug("" + ex.getStackTrace());
			logger.debug("end print camel exception information:");
		}

	}
}
