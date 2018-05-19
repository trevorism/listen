package com.trevorism.event.handler

import com.trevorism.event.model.EventData
import com.trevorism.event.model.EventDataTest
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

    @Test
    void testConvertEventData(){
        StoreEventHandler handler = new StoreEventHandler()
        EventData eventData = EventDataTest.createTestEventData()
        def result = handler.convertEventIntoPostObject(eventData)

        assert result.dateCreated
        assert result.correlationId == "1234"
        assert result.test == "value"

    }
}
