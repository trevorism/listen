package com.trevorism.event.handler

import com.google.gson.Gson
import com.trevorism.event.model.EventData
import com.trevorism.http.headers.HeadersHttpClient
import org.junit.Test

/**
 * @author tbrooks
 */
class VmEventHandlerTest {

    @Test
    void testGetPingUrl() {
        VmEventHandler handler = new VmEventHandler()
        assert "https://azure-vm-dot-trevorism-gcloud.appspot.com/ping" == handler.getPingUrl()
    }

    @Test
    void testGetPostUrl(){
        VmEventHandler handler = new VmEventHandler()
        assert "https://azure-vm-dot-trevorism-gcloud.appspot.com/vm/" == handler.getPostUrl(null)
    }

    @Test
    void testConvertEventIntoPostObject(){
        VmEventHandler handler = new VmEventHandler()
        def result = handler.convertEventIntoPostObject(null)
        assert [:] == result
        assert "{}" == new Gson().toJson(result)
    }
}
