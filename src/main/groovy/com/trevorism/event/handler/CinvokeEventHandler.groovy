package com.trevorism.event.handler

import com.trevorism.event.exception.EventNotRoutedException
import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class CinvokeEventHandler extends AbstractPingingEventHandler {

    @Override
    String getPingUrl() {
        "https://cinvoke-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Override
    String getPostUrl(EventData eventData) {
        String name = eventData?.data?.name
        if(!name)
            throw new EventNotRoutedException("Must specify a 'name' in the cinvoke event")

        "https://cinvoke-dot-trevorism-gcloud.appspot.com/job/${name}/build"
    }

    @Override
    protected convertEventIntoPostObject(EventData event) {
        return [:]
    }

}
