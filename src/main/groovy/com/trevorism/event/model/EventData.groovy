package com.trevorism.event.model

/**
 * @author tbrooks
 */
class EventData {
    String correlationId
    String topic
    String publishTime
    Map data = [:]

    private EventData(){}

    static EventData createFromMap(Map map){
        create(ReceivedEvent.create(map))
    }

    static EventData create(ReceivedEvent event){
        EventData eventData = new EventData()
        eventData.data = event.message.data

        if(event.message.attributes.containsKey("correlationId"))
            eventData.correlationId = event.message.attributes["correlationId"]

        eventData.topic = event.message.attributes["topic"]
        eventData.publishTime = event.message.publishTime

        return eventData
    }
}
