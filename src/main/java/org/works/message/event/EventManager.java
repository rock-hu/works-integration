package org.works.message.event;

import org.mule.api.FutureMessageResult;
import org.w3c.dom.events.EventException;

public interface EventManager
{
   /**
    * Sends an event message synchronously to a given service.
    *
    * @param serviceName    The name of the service to which the event
    *                       message is to be sent.
    * @param payload        The content of the event message.
    * @return Object        The result, if any.
    * @throws EventException on error
    */
   public Object sendSynchronousEvent(String serviceName,
                                      Object payload)
      throws EventException;

                       
 /**
    * Sends an event message asynchronously to a given service.
    *
    * @param serviceName    The name of the service to which the event
    *                       message is to be sent.
    * @param payload        The content of the event message.
    * @return FutureMessageResult The result, if any.
    * @throws EventException on error
    */
   public FutureMessageResult sendAsynchronousEvent(String serviceName,
                                                    Object payload)
      throws EventException;

                       
 /**
    * Starts this event manager.
    */
   public void start();

                       
 /**
    * Stops this event manager.
    */
   public void stop();

                       
 /**
    * Retrieves the protocol this event manager uses.
    * @return
    */
   public String getProtocol();

                       
 /**
    * Registers a service to receive event messages.
    *
    * @param serviceName      The name to associate with the service.
    * @param implementation   Either a container reference to the service
    *                         or a fully-qualified class name.
    */
   public void registerService(String serviceName,
                               String implementation)
      throws EventException;

                       
 /**
    * Unregisters a service from receiving event messages.
    *
    * @param serviceName  The name associated with the service to unregister.
    */
   public void unregisterService(String serviceName)
      throws EventException;
}
