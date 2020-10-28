package com.trevorism.event.webapi.controller

import com.trevorism.event.handler.EventHandlerFactory
import com.trevorism.event.model.EventData
import io.swagger.annotations.Api

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.MediaType
import java.util.logging.Logger

/**
 * @author tbrooks
 */
@Api("Listener Operations")
@Path("/_ah/push-handlers")
class EventListenerController {


    private static final Logger log = Logger.getLogger(EventListenerController.class.name)

    @POST
    @Path("store_{topic}")
    @Consumes(MediaType.APPLICATION_JSON)
    void store(@PathParam("topic") String topic, Map<String, Object> data){
        EventData eventData = EventData.createFromMap(data)
        EventHandlerFactory.build("store").performAction(eventData)
    }

    @POST
    @Path("handle_{topic}")
    @Consumes(MediaType.APPLICATION_JSON)
    void handle(@PathParam("topic") String topic, Map<String, Object> data){
        StringBuilder logBuilder = new StringBuilder()
        data.each {k,v ->
            logBuilder << "$k:$v\n"
        }
        log.warning(logBuilder.toString())
        EventData eventData = EventData.createFromMap(data)
        EventHandlerFactory.build(topic).performAction(eventData)

    }

}
