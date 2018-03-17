package com.trevorism.event.handler

import com.trevorism.event.model.EventData
import com.trevorism.http.headers.HeadersHttpClient
import org.junit.Test

/**
 * @author tbrooks
 */
class StoreEventHandlerTest {

    @Test
    void testGetPingUrl() {
        StoreEventHandler handler = new StoreEventHandler()
        assert "https://datastore.trevorism.com/ping" == handler.getPingUrl()
    }

    @Test
    void testHandleEvent() {
        StoreEventHandler handler = new StoreEventHandler()
        handler.client = [post : {s1,s2,map -> assert s1 == "https://datastore.trevorism.com/api/test/" }] as HeadersHttpClient

        handler.handleEvent(new EventData(topic: "test"))
    }
}
