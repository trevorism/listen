package com.trevorism.event.handler

import com.google.gson.Gson
import com.trevorism.event.model.EventData
import com.trevorism.event.model.ReceivedEvent
import com.trevorism.http.HttpClient
import com.trevorism.http.JsonHttpClient
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.headers.HeadersJsonHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.secure.PasswordProvider

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class TestResultEventHandler extends AbstractPingingEventHandler{

    private static final Logger log = Logger.getLogger(TestResultEventHandler.class.name)

    @Override
    String getPingUrl() {
        "https://email-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Override
    void handleEvent(EventData eventData) {
        //Only email if the test failed
        if(eventData.data.passing)
            return
        String json = createEmailJson(eventData)
        log.info("Correlation ID: ${eventData.correlationId}")
        log.info("HTTP POST: ${json}")
        Map headerMap = ["X-Correlation-ID": eventData.correlationId, "Authorization": passwordProvider.password]
        ResponseUtils.closeSilently client.post('https://email-dot-trevorism-gcloud.appspot.com/mail/', json, headerMap)
    }

    private String createEmailJson(EventData event) {
        def email = buildEmail(event)
        return gson.toJson(email)
    }

    private static def buildEmail(EventData event) {
        def email = [:]
        email["subject"] = "Test for ${event.data.feature} failed".toString()
        email["recipients"] = ["alerts@trevorism.com"]
        email["body"] = buildMessage(event.data)
        email
    }

    private static String buildMessage(def data) {
        "Test failed for scenario: ${data.name}\n\n${data?.given}\n${data?.when}\n${data?.then}\n\n${data?.errorMessage}"
    }

}
