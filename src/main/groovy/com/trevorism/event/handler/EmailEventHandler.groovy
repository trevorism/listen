package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class EmailEventHandler extends AbstractPingingEventHandler {

    @Override
    String getPingUrl() {
        "https://email-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Override
    String getPostUrl(EventData eventData) {
        'https://email-dot-trevorism-gcloud.appspot.com/mail/'
    }

}
