package com.trevorism.event.handler

import com.trevorism.event.model.EventData

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class LoggingEventHandler implements EventHandler{

    private static final Logger log = Logger.getLogger(LoggingEventHandler.class.name)

    @Override
    void performAction(EventData eventData) {
        log.info("Correlation ID: ${eventData?.correlationId}")
        log.info("Event Data: ${eventData?.data}")
    }
}
