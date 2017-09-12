package com.trevorism.event.webapi.controller

import com.trevorism.event.handler.LoggingEventHandler
import com.trevorism.event.handler.StoreEventHandler
import com.trevorism.event.model.ReceivedEvent

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

/**
 * @author tbrooks
 */
@Path("/_ah/push-handlers")
class EventListenerController {

    private StoreEventHandler storeEventHandler = new StoreEventHandler()
    private LoggingEventHandler loggingEventHandler = new LoggingEventHandler()


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<String> endpoints(){
        return ["get", "post"]
    }

    @POST
    @Path("store_{topic}")
    @Consumes(MediaType.APPLICATION_JSON)
    void store(@PathParam("topic") String topic, Map<String, Object> data){
        ReceivedEvent event = ReceivedEvent.create(data)
        storeEventHandler.performAction(event)
    }

    @POST
    @Path("handle_{topic}")
    @Consumes(MediaType.APPLICATION_JSON)
    void handle(@PathParam("topic") String topic, Map<String, Object> data){
        ReceivedEvent event = ReceivedEvent.create(data)
        loggingEventHandler.performAction(event)
    }
}
