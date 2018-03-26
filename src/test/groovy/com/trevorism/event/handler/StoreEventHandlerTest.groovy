package com.trevorism.event.handler

import com.trevorism.event.model.EventData
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
    void testGetPostUrl(){
        StoreEventHandler handler = new StoreEventHandler()
        assert "https://datastore.trevorism.com/api/test/" == handler.getPostUrl(new EventData(topic: "test"))
    }
}
