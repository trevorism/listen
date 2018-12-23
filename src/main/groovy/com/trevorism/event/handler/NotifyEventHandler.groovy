package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class NotifyEventHandler extends AbstractPingingEventHandler {

    @Override
    String getPingUrl() {
        "https://trevorism-scheduler.azurewebsites.net/ping"
    }

    @Override
    String getPostUrl(EventData eventData) {
        "https://trevorism-scheduler.azurewebsites.net/schedule/notify"
    }

    @Override
    protected convertEventIntoPostObject(EventData event) {
        Random random = new Random()
        String randomNumber = (random.nextInt(1000000) + 1000000).toString()

        return [name:randomNumber, collectionName:"notify", startDate: event.data.get("datetime"),
                enabled:"true", endpoint:"https://alert-dot-trevorism-gcloud.appspot.com/alert/",
                httpMethod: "POST", requestBody:"{\"subject\":\"Notification originating from ${event.publishTime}\",\"body\":\"${event.data.get("content")}\""]
    }

}

