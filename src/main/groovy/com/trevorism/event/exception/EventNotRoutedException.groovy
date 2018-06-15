package com.trevorism.event.exception

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class EventNotRoutedException extends RuntimeException{

    private static final Logger log = Logger.getLogger(EventNotRoutedException.class.name)

    EventNotRoutedException(String message){
        super(message)
        log.warning(message)

    }

}
