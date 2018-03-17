package com.trevorism.event.handler

/**
 * @author tbrooks
 */
class EventHandlerFactory {

    static EventHandler build(String key) {

        if ("testresult" == key?.toLowerCase())
            return new TestResultEventHandler()

        if ("email" == key?.toLowerCase())
            return new EmailEventHandler()

        if ("store" == key?.toLowerCase())
            return new StoreEventHandler()

        return new LoggingEventHandler()
    }

}
