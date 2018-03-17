package com.trevorism.event.handler

import com.trevorism.event.model.EventData
import com.trevorism.http.headers.HeadersHttpClient
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
    void testHandleEvent() {
        TestResultEventHandler handler = new TestResultEventHandler()
        handler.client = [post : {s1,s2,map -> assert s1 == "https://email-dot-trevorism-gcloud.appspot.com/mail/" }] as HeadersHttpClient

        handler.handleEvent(new EventData())
    }
}
