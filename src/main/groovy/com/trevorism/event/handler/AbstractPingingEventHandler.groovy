package com.trevorism.event.handler

import com.google.gson.Gson
import com.trevorism.event.model.EventData
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.headers.HeadersJsonHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.secure.PasswordProvider

import java.util.logging.Logger

/**
 * @author tbrooks
 */
abstract class AbstractPingingEventHandler implements EventHandler {

    private static final Logger log = Logger.getLogger(AbstractPingingEventHandler.class.name)
    private static final int PING_TIMEOUT_MILLIS = 10000

    private HeadersHttpClient client = new HeadersJsonHttpClient()
    private Gson gson = new Gson()

    protected abstract String getPingUrl()
    protected abstract String getPostUrl(EventData eventData)

    @Override
    void performAction(EventData event) {
        ping(pingUrl)
        String json = gson.toJson(convertEventIntoPostObject(event))
        log.info("Correlation ID: ${event.correlationId}")
        log.info("${getPostUrl(event)} -- HTTP POST: ${json}")
        Map headerMap = ["X-Correlation-ID": event.correlationId, "Authorization": "bearer ${event.token}".toString()]
        ResponseUtils.closeSilently client.post(getPostUrl(event), json, headerMap)
    }

    protected def convertEventIntoPostObject(EventData eventData) {
        return eventData.data
    }

    private static void ping(String url) {
        try {
            new URL(url).text
        } catch (Exception ignored) {
            Thread.sleep(PING_TIMEOUT_MILLIS)
            new URL(url).text
        }
    }
}
