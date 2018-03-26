package com.trevorism.event.model

import com.trevorism.event.webapi.controller.EventListenerControllerTest
import org.junit.Test

/**
 * @author tbrooks
 */
class EventDataTest {

    @Test
    void testCreate() {
        SubscriptionInfo info = new SubscriptionInfo(subscription: "testSubscription")
        Message message = new Message(data: ["test":"value"], publishTime: "2018", attributes: ["topic":"testTopic", "correlationId":"1234"])
        ReceivedEvent receivedEvent = new ReceivedEvent(subscription: info, message: message)

        EventData eventData = EventData.create(receivedEvent)

        assert eventData.data
        assert eventData.data["test"] == "value"
        assert eventData.topic == "testTopic"
        assert eventData.publishTime == "2018"
        assert eventData.correlationId == "1234"

    }

    @Test
    void testCreateFromMap(){
        Map map = EventListenerControllerTest.createEmptyEvent()
        ReceivedEvent event = ReceivedEvent.create(map)
        EventData data = EventData.create(event)
        assert data.data == [:]
        assert !data.correlationId
        assert !data.topic
        assert !data.publishTime
    }
}
