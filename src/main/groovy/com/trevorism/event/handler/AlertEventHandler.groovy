package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class AlertEventHandler extends AbstractPingingEventHandler {

    @Override
    String getPingUrl() {
        "https://alert-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Override
    String getPostUrl(EventData eventData) {
        'https://alert-dot-trevorism-gcloud.appspot.com/alert/'
    }

}