package com.trevorism.event.handler

import com.google.gson.Gson
import com.trevorism.event.model.EventData
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.headers.HeadersJsonHttpClient
import com.trevorism.secure.PasswordProvider

/**
 * @author tbrooks
 */
abstract class AbstractPingingEventHandler implements EventHandler{

    public static final int PING_TIMEOUT_MILLIS = 10000

    protected HeadersHttpClient client = new HeadersJsonHttpClient()
    protected PasswordProvider passwordProvider = new PasswordProvider()
    protected Gson gson = new Gson()

    abstract String getPingUrl()
    abstract void handleEvent(EventData eventData)

    @Override
    void performAction(EventData event) {
        ping(pingUrl)
        handleEvent(event)
    }

    void ping(String url) {
        try {
            new URL(url).text
        } catch (Exception ignored) {
            Thread.sleep(PING_TIMEOUT_MILLIS)
            new URL(url).text
        }
    }
}
