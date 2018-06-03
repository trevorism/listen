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
class EmailEventHandlerTest {

    @Test
    void testGetPingUrl() {
        EmailEventHandler handler = new EmailEventHandler()
        assert "https://email-dot-trevorism-gcloud.appspot.com/ping" == handler.getPingUrl()
    }

    @Test
    void testGetPostUrl(){
        EmailEventHandler handler = new EmailEventHandler()
        assert "https://email-dot-trevorism-gcloud.appspot.com/mail/" == handler.getPostUrl(null)
    }

    @Test
    void testConvertEventIntoPostObject(){
        EmailEventHandler handler = new EmailEventHandler()
        def result1 = handler.convertEventIntoPostObject(createTestEmailData(["subject": "testSubject", "recipients":"trev@gmail.com, trev2@gmail.com", "body": "testBody"]))
        def result2 = handler.convertEventIntoPostObject(createTestEmailData(["subject": "testSubject", "recipients":"trev@gmail.com; trev2@gmail.com", "body": "testBody"]))
        def result3 = handler.convertEventIntoPostObject(createTestEmailData(["subject": "testSubject", "recipients":["trev@gmail.com", "trev2@gmail.com"], "body": "testBody"]))

        assert new Gson().toJson(result1) == new Gson().toJson(result2)
        assert new Gson().toJson(result3) == new Gson().toJson(result2)

    }

    static EventData createTestEmailData(Map dataMap) {
        SubscriptionInfo info = new SubscriptionInfo(subscription: "testSubscription")
        Message message = new Message(data: dataMap, publishTime: "2018", attributes: ["topic": "testTopic", "correlationId": "1234"])
        ReceivedEvent receivedEvent = new ReceivedEvent(subscription: info, message: message)

        EventData eventData = EventData.create(receivedEvent)
        eventData
    }
}
