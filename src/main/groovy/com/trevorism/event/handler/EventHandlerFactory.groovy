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

        if ("alert" == key?.toLowerCase())
            return new AlertEventHandler()

        if ("store" == key?.toLowerCase())
            return new StoreEventHandler()

        if ("startvm" == key?.toLowerCase())
            return new VmEventHandler()

        if ("deploy" == key?.toLowerCase())
            return new DeployEventHandler()

        if ("cinvoke" == key?.toLowerCase())
            return new CinvokeEventHandler()

        return new LoggingEventHandler()
    }

}
