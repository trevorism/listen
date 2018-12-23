package com.trevorism.event.handler

import com.google.gson.Gson
import com.trevorism.event.model.EventData
import com.trevorism.event.model.Message
import com.trevorism.event.model.ReceivedEvent
import com.trevorism.event.model.SubscriptionInfo
import org.junit.Test

/**
 * @author tbrooks
 */
class NotifyEventHandlerTest {

    @Test
    void testGetPingUrl() {
        NotifyEventHandler notifyEventHandler = new NotifyEventHandler()
        assert notifyEventHandler.pingUrl == "https://trevorism-scheduler.azurewebsites.net/ping"
    }

    @Test
    void testGetPostUrl() {
        NotifyEventHandler notifyEventHandler = new NotifyEventHandler()
        assert notifyEventHandler.getPostUrl(null ) == "https://trevorism-scheduler.azurewebsites.net/schedule/notify"
    }

    @Test
    void testConvertEventIntoPostObject() {
        NotifyEventHandler notifyEventHandler = new NotifyEventHandler()
        EventData eventData = createTestNotification(["content":"Hello World", "datetime":"2018-12-23T15:00:00Z"])

        def obj = notifyEventHandler.convertEventIntoPostObject(eventData)
        assert obj["requestBody"].contains("subject")
        assert obj["requestBody"].contains("body")
        assert !obj["requestBody"].contains("strings")
        assert obj["endpoint"] == "https://alert-dot-trevorism-gcloud.appspot.com/alert/"
        assert obj["enabled"]

    }

    static EventData createTestNotification(Map dataMap) {
        SubscriptionInfo info = new SubscriptionInfo(subscription: "testSubscription")
        Message message = new Message(data: dataMap, publishTime: "2018", attributes: ["topic": "testTopic", "correlationId": "1234"])
        ReceivedEvent receivedEvent = new ReceivedEvent(subscription: info, message: message)

        EventData eventData = EventData.create(receivedEvent)
        eventData
    }
}
