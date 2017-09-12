package com.trevorism.event.handler

import com.trevorism.event.model.ReceivedEvent

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class LoggingEventHandler implements EventHandler{

    private static final Logger log = Logger.getLogger(LoggingEventHandler.class.name)

    @Override
    void performAction(ReceivedEvent event) {
        log.info("Received Event: ${event.message.data}")
    }
}
