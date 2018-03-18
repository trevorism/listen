package com.trevorism.event.handler

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
    void testHandleEvent() {
        VmEventHandler handler = new VmEventHandler()
        handler.client = [post : {s1,s2,map -> assert s1 == "https://azure-vm-dot-trevorism-gcloud.appspot.com/vm/" }] as HeadersHttpClient

        handler.handleEvent(new EventData())
    }
}
