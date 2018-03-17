package com.trevorism.gcloud.webapi.controller

import com.trevorism.event.webapi.controller.RootController
import org.junit.Test

import javax.ws.rs.core.Response


/**
 * @author tbrooks
 */
class RootControllerTest {

    @Test
    void testRootControllerEndpoints(){
        RootController rootController = new RootController()
        assert rootController.displayHelpLink().contains("/help")
    }

    @Test
    void testRootControllerPing(){
        RootController rootController = new RootController()
        assert rootController.ping() == "pong"
    }

    @Test
    void testHelpRedirects(){
        RootController rootController = new RootController()
        Response response = rootController.help()
        assert 307 == response.getStatus()
        assert response.location.toString() == "/swagger/index.html"
    }
}
