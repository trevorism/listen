package com.trevorism.event.handler

import org.junit.Test

/**
 * @author tbrooks
 */
class TestResultEventHandlerTest {

    @Test
    void testGetPingUrl() {
        TestResultEventHandler handler = new TestResultEventHandler()
        assert handler.pingUrl == "https://email-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Test
    void testGetPostUrl(){
        TestResultEventHandler handler = new TestResultEventHandler()
        assert "https://email-dot-trevorism-gcloud.appspot.com/mail/" == handler.getPostUrl(null)
    }
}
