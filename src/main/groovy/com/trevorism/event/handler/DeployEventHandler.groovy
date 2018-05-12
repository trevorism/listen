package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class DeployEventHandler extends AbstractPingingEventHandler {

    @Override
    protected String getPingUrl() {
        return "https://registry-dot-trevorism-gcloud.appspot.com/api/ping"
    }

    @Override
    protected String getPostUrl(EventData eventData) {
        return "https://registry-dot-trevorism-gcloud.appspot.com/api/deploy"
    }
}
