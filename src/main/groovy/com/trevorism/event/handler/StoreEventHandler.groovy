package com.trevorism.event.handler

import com.google.gson.Gson
import com.trevorism.event.model.ReceivedEvent
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.headers.HeadersJsonHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.secure.PasswordProvider

/**
 * @author tbrooks
 */
class StoreEventHandler implements EventHandler {

    HeadersHttpClient client = new HeadersJsonHttpClient()
    PasswordProvider passwordProvider = new PasswordProvider()

    @Override
    void performAction(ReceivedEvent event) {
        String topic = event.message.attributes["topic"]
        def dataToStore = createDataForStorage(event)

        Gson gson = new Gson()
        String json = gson.toJson(dataToStore)

        ResponseUtils.closeSilently client.post("http://datastore.trevorism.com/api/${topic}/", json, [Authorization: passwordProvider.password])
    }

    private def createDataForStorage(ReceivedEvent event) {
        def dataToStore = [:]
        dataToStore["dateCreated"] = event.message.publishTime
        if(event.message.data)
            dataToStore.putAll(event.message.data)
        dataToStore
    }

}
