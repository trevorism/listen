package com.trevorism.event.handler

import com.google.gson.Gson
import com.trevorism.event.model.EventData
import com.trevorism.http.util.ResponseUtils

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class StoreEventHandler extends AbstractPingingEventHandler {

    private static final Logger log = Logger.getLogger(StoreEventHandler.class.name)

    @Override
    String getPingUrl() {
        "https://datastore.trevorism.com/ping"
    }

    @Override
    void handleEvent(EventData eventData) {
        def dataToStore = createDataForStorage(eventData)
        String json = gson.toJson(dataToStore)
        log.info("Correlation ID: ${eventData.correlationId}")
        log.info("HTTP POST: ${json}")
        Map headerMap = ["X-Correlation-ID": eventData.correlationId, "Authorization": passwordProvider.password]
        ResponseUtils.closeSilently client.post("https://datastore.trevorism.com/api/${eventData.topic}/", json, headerMap)
    }

    private static createDataForStorage(EventData eventData) {
        def dataToStore = [dateCreated: eventData.publishTime]
        if(eventData.data)
            dataToStore.putAll(eventData.data)
        dataToStore
    }

}
