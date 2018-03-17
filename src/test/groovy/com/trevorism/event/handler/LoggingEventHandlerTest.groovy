package com.trevorism.event.handler

import org.junit.Test

/**
 * @author tbrooks
 */
class LoggingEventHandlerTest {

    @Test
    void testPerformActionWithNullDoesNotCrash() {
        LoggingEventHandler loggingEventHandler = new LoggingEventHandler()
        loggingEventHandler.performAction(null)
        assert true
    }
}
