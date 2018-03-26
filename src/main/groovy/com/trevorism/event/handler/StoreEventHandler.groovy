package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class StoreEventHandler extends AbstractPingingEventHandler {

    @Override
    String getPingUrl() {
        "https://datastore.trevorism.com/ping"
    }

    @Override
    String getPostUrl(EventData eventData) {
        "https://datastore.trevorism.com/api/${eventData.topic}/"
    }

    @Override
    protected def convertEventIntoPostObject(EventData eventData) {
        def dataToStore = [dateCreated: eventData.publishTime]
        if (eventData.correlationId)
            dataToStore.put("correlationId", eventData.correlationId)
        if (eventData.data)
            dataToStore.putAll(eventData.data)
        dataToStore
    }

}
