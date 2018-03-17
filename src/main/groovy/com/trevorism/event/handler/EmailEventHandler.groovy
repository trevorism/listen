package com.trevorism.event.handler

import com.trevorism.event.model.EventData
import com.trevorism.http.util.ResponseUtils

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class EmailEventHandler extends AbstractPingingEventHandler {

    private static final Logger log = Logger.getLogger(EmailEventHandler.class.name)

    @Override
    String getPingUrl() {
        "https://email-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Override
    void handleEvent(EventData event) {
        String json = gson.toJson(event.data)
        log.info("Correlation ID: ${event.correlationId}")
        log.info("HTTP POST: ${json}")
        Map headerMap = ["X-Correlation-ID": event.correlationId, "Authorization": passwordProvider.password]
        ResponseUtils.closeSilently client.post('https://email-dot-trevorism-gcloud.appspot.com/mail/', json, headerMap)
    }

}
