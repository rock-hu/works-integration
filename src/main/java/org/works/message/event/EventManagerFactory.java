package org.works.message.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.MessageExchangePattern;
import org.mule.api.DefaultMuleException;
import org.mule.api.FutureMessageResult;
import org.mule.api.MuleContext;
import org.mule.api.client.MuleClient;
import org.mule.api.endpoint.EndpointMessageProcessorChainFactory;
import org.mule.api.endpoint.EndpointURI;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.retry.RetryPolicyTemplate;
import org.mule.api.transaction.TransactionConfig;
import org.mule.api.transport.Connector;
import org.mule.client.DefaultLocalMuleClient;
import org.mule.config.spring.SpringConfigurationBuilder;
import org.mule.endpoint.DefaultInboundEndpoint;
import org.mule.endpoint.DefaultOutboundEndpoint;
import org.mule.processor.AbstractRedeliveryPolicy;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.events.EventException;

public class EventManagerFactory {
	private static HashMap instances = new HashMap();

	private static ApplicationContext appContext;

	
	/**
	 * Retrieves the event manager instance for a given protocol.
	 * 
	 * @param protocol
	 *            The protocol to use.
	 * @return EventManager The event manager instance.
	 */
	public static EventManager getInstance(String protocol) {
		EventManager instance = (EventManager) instances.get(protocol);

		if (instance == null) {
			instance = new EventManagerImpl(protocol);
			instances.put(protocol, instance);
		}

		return instance;
	}

	/**
	 * A concrete implementation for a simple event manager.
	 */
	private static class EventManagerImpl implements EventManager {
		private UMOManager manager = null;
		private SpringConfigurationBuilder builder = null;
		private MuleClient eventClient = null;
		private String protocol = null;
		private InboundEndpoint receiveEndpoint = null;
		private OutboundEndpoint sendEndpoint = null;
		
		protected final MuleContext muleContext;

		private EventManagerImpl(String protocol) {
			this.protocol = protocol;
		}

		/**
		 * Starts this event manager.
		 */
		public void start() {
			try {
				builder = new SpringConfigurationBuilder(appContext);
				manager = builder.createStartedManager(true, protocol + "tmp/events");
				eventClient = new DefaultLocalMuleClient(muleContext);
				receiveEndpoint = new DefaultInboundEndpoint(
						Connector connector,
                        EndpointURI endpointUri,
                        String name,
                        Map properties,
                        TransactionConfig transactionConfig,
                        boolean deleteUnacceptedMessage,
                        MessageExchangePattern messageExchangePattern,
                        int responseTimeout,
                        String initialState,
                        String endpointEncoding,
                        String endpointBuilderName,
                        MuleContext muleContext,
                        RetryPolicyTemplate retryPolicyTemplate,
                        AbstractRedeliveryPolicy redeliveryPolicy,
                        EndpointMessageProcessorChainFactory messageProcessorsFactory,
                        List <MessageProcessor> messageProcessors,
                        List <MessageProcessor> responseMessageProcessors,
                        boolean disableTransportTransformer,
                        String mimeType
						);
				sendEndpoint = new DefaultOutboundEndpoint(protocol + "tmp/events/send");
			} catch (DefaultMuleException e) {
				System.err.println(e);
			}
		}

		/**
		 * Stops this event manager.
		 */
		public void stop() {
			try {
				manager.stop();
			} catch (DefaultMuleException e) {
				System.err.println(e);
			}
		}

		/**
		 * Retrieves the protocol this event manager uses.
		 * 
		 * @return
		 */
		public String getProtocol() {
			return protocol;
		}

		/**
		 * Registers a service to receive event messages.
		 * 
		 * @param serviceName
		 *            The name to associate with the service.
		 * @param implementation
		 *            Either a container reference to the service or a
		 *            fully-qualified class name to use as the component
		 *            implementation.
		 */
		public void registerService(String serviceName, String implementation) throws EventException {
			if (!manager.getModel().isComponentRegistered(serviceName)) {
				try {
					builder.registerComponent(implementation, serviceName, receiveEndpoint, sendEndpoint);
				} catch (DefaultMuleException e) {
					throw new EventException(e.toString());
				}
			}
		}

		/**
		 * Unregisters a service from receiving event messages.
		 * 
		 * @param serviceName
		 *            The name associated with the service to unregister.
		 */
		public void unregisterService(String serviceName) throws EventException {
			try {
				builder.unregisterComponent(serviceName);
			} catch (DefaultMuleException e) {
				throw new EventException(e.toString());
			}
		}

		/**
		 * Sends an event message synchronously to a given service.
		 * 
		 * @param serviceName
		 *            The name of the service to which the event message is to
		 *            be sent.
		 * @param payload
		 *            The content of the event message
		 * @return Object The result, if any.
		 * @throws EventException
		 *             on error
		 */
		public Object sendSynchronousEvent(String serviceName, Object payload) throws EventException {
			try {
				if (!manager.getModel().isComponentRegistered(serviceName)) {
					throw new EventException("Service: " + serviceName + " is not registered.");
				}

				String transformers = null;
				Map messageProperties = null;
				UMOMessage result = eventClient.sendDirect(serviceName, transformers, payload, messageProperties);
				if (result == null) {
					return null;
				}
				return result.getPayload();
			} catch (UMOException e) {
				throw new EventException(e.toString());
			} catch (Exception e) {
				throw new EventException(e.toString());
			}
		}

		/**
		 * Sends an event message asynchronously.
		 * 
		 * @param serviceName
		 *            The name of the service to which the event message is to
		 *            be sent.
		 * @param payload
		 *            The content of the event message.
		 * @return FutureMessageResult The result, if any
		 * @throws EventException
		 *             on error
		 */
		public FutureMessageResult sendAsynchronousEvent(String serviceName, Object payload) throws EventException {
			FutureMessageResult result = null;

			try {
				if (!manager.getModel().isComponentRegistered(serviceName)) {
					throw new EventException("Service: " + serviceName + " is not registered.");
				}

				String transformers = null;
				Map messageProperties = null;
				result = eventClient.sendDirectAsync(serviceName, transformers, payload, messageProperties);
			} catch (UMOException e) {
				throw new EventException(e.toString());
			}

			return result;
		}
	}
}
