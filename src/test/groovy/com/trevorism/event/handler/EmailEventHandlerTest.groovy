package com.trevorism.event.handler

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
    void testGetPostUrl(){
        EmailEventHandler handler = new EmailEventHandler()
        assert "https://email-dot-trevorism-gcloud.appspot.com/mail/" == handler.getPostUrl(null)
    }

}
