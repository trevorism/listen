package com.trevorism.event.handler

import com.trevorism.event.model.EventData
import com.trevorism.http.util.ResponseUtils

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class VmEventHandler extends AbstractPingingEventHandler{

    private static final Logger log = Logger.getLogger(VmEventHandler.class.name)

    @Override
    String getPingUrl() {
        return "https://azure-vm-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Override
    void handleEvent(EventData eventData) {
        log.info("Correlation ID: ${eventData.correlationId}")
        Map headerMap = ["X-Correlation-ID": eventData.correlationId, "Authorization": passwordProvider.password]
        ResponseUtils.closeSilently client.post('https://azure-vm-dot-trevorism-gcloud.appspot.com/vm/', "{}", headerMap)
    }
}
