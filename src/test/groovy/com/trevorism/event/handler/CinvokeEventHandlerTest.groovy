package com.trevorism.event.handler

import com.trevorism.event.exception.EventNotRoutedException
import com.trevorism.event.model.EventData
import org.junit.Test

/**
 * @author tbrooks
 */
class CinvokeEventHandlerTest {

    @Test
    void testGetPingUrl() {
        CinvokeEventHandler handler = new CinvokeEventHandler()
        assert "https://cinvoke-dot-trevorism-gcloud.appspot.com/ping" == handler.getPingUrl()
    }

    @Test
    void testGetPostUrl() {
        CinvokeEventHandler handler = new CinvokeEventHandler()
        assert "https://cinvoke-dot-trevorism-gcloud.appspot.com/job/unittest/build" == handler.getPostUrl(new EventData(data: [name:"unittest"]))
    }

    @Test
    void testConvertEventIntoPostObject() {
        CinvokeEventHandler handler = new CinvokeEventHandler()
        assert !handler.convertEventIntoPostObject(new EventData())
        assert !handler.convertEventIntoPostObject(null)
    }

    @Test(expected = EventNotRoutedException)
    void testGetPostUrlNoData() {
        CinvokeEventHandler handler = new CinvokeEventHandler()
        handler.getPostUrl(null)
    }
}
