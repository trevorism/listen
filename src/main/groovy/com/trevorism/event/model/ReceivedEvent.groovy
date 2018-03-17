package com.trevorism.event.model

import com.trevorism.event.webapi.serialize.JacksonConfig

/**
 * @author tbrooks
 */
class ReceivedEvent {

    private ReceivedEvent(){}

    Message message
    SubscriptionInfo subscription

    static create(def map){
        Message msg = new Message()
        msg.attributes = map["message"]["attributes"]
        msg.publishTime = map["message"]["publishTime"]

        String json = new String(map["message"]["data"].toString().decodeBase64())
        msg.data = JacksonConfig.objectMapper.readValue(json, Map.class)

        SubscriptionInfo info = new SubscriptionInfo([subscription: map["subscription"]])

        return new ReceivedEvent(message: msg, subscription: info)
    }

}
