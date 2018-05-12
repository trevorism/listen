package com.trevorism.event.handler

import org.junit.Test

/**
 * @author tbrooks
 */
class DeployEventHandlerTest extends GroovyTestCase {

    @Test
    void testGetPingUrl() {
        DeployEventHandler handler = new DeployEventHandler()
        assert "https://registry-dot-trevorism-gcloud.appspot.com/api/ping" == handler.getPingUrl()
    }

    @Test
    void testGetPostUrl() {
        DeployEventHandler handler = new DeployEventHandler()
        assert "https://registry-dot-trevorism-gcloud.appspot.com/api/deploy" == handler.getPostUrl()
    }
}
