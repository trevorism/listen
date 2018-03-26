package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class VmEventHandler extends AbstractPingingEventHandler {

    @Override
    String getPingUrl() {
        "https://azure-vm-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Override
    String getPostUrl(EventData eventData) {
        'https://azure-vm-dot-trevorism-gcloud.appspot.com/vm/'
    }

    @Override
    protected convertEventIntoPostObject(EventData eventData) {
        [:]
    }
}
