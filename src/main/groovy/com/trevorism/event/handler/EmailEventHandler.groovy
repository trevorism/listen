package com.trevorism.event.handler

import com.google.gson.Gson
import com.trevorism.event.model.ReceivedEvent
import com.trevorism.http.HttpClient
import com.trevorism.http.JsonHttpClient

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class EmailEventHandler implements EventHandler{

    private HttpClient client = new JsonHttpClient()
    private static final Logger log = Logger.getLogger(EmailEventHandler.class.name)

    @Override
    void performAction(ReceivedEvent event) {
        //Only email if the test failed
        if(event.message.data.passing)
            return
        ping()
        String json = createEmailJson(event)
        log.info("POSTING ${json}")
        client.post('https://email-dot-trevorism-gcloud.appspot.com/mail/', json)
    }

    private static String createEmailJson(ReceivedEvent event) {
        Gson gson = new Gson()
        return gson.toJson(event.message.data)
    }

    private void ping() {
        try {
            new URL("https://email-dot-trevorism-gcloud.appspot.com/ping").text
        } catch (Exception ignored) {
            Thread.sleep(10000)
            new URL("https://email-dot-trevorism-gcloud.appspot.com/ping").text
        }
    }
}
