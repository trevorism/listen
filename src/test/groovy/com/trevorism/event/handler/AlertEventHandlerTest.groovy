package com.trevorism.event.handler

import org.junit.Test

/**
 * @author tbrooks
 */
class AlertEventHandlerTest{

    @Test
    void testGetPingUrl() {
        AlertEventHandler handler = new AlertEventHandler()
        assert "https://alert-dot-trevorism-gcloud.appspot.com/ping" == handler.getPingUrl()
    }

    @Test
    void testGetPostUrl(){
        AlertEventHandler handler = new AlertEventHandler()
        assert "https://alert-dot-trevorism-gcloud.appspot.com/alert/" == handler.getPostUrl(null)
    }

}
