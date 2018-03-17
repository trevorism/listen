package com.trevorism.event.handler

import com.trevorism.event.model.EventData
import com.trevorism.http.headers.HeadersHttpClient
import org.junit.Test

/**
 * @author tbrooks
 */
class EmailEventHandlerTest {

    @Test
    void testGetPingUrl() {
        EmailEventHandler handler = new EmailEventHandler()
        assert "https://email-dot-trevorism-gcloud.appspot.com/ping" == handler.getPingUrl()
    }

    @Test
    void testHandleEvent() {
        EmailEventHandler handler = new EmailEventHandler()
        handler.client = [post : {s1,s2,map -> assert s1 == "https://email-dot-trevorism-gcloud.appspot.com/mail/" }] as HeadersHttpClient

        handler.handleEvent(new EventData())
    }
}
