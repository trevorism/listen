package com.trevorism.event.handler

import org.junit.Test

/**
 * @author tbrooks
 */
class EventHandlerFactoryTest {

    @Test
    void testBuild() {
        assert EventHandlerFactory.build("store") instanceof StoreEventHandler
        assert EventHandlerFactory.build("log") instanceof LoggingEventHandler
        assert EventHandlerFactory.build("foo") instanceof LoggingEventHandler
        assert EventHandlerFactory.build("testresult") instanceof TestResultEventHandler
        assert EventHandlerFactory.build("email") instanceof EmailEventHandler
        assert EventHandlerFactory.build("startvm") instanceof VmEventHandler
        assert EventHandlerFactory.build("deploy") instanceof DeployEventHandler


    }
}
