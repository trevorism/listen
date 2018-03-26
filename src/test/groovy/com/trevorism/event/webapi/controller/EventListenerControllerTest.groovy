package com.trevorism.event.webapi.controller

import com.trevorism.event.handler.EventHandlerFactory
import com.trevorism.event.model.EventData
import org.junit.Test

/**
 * @author tbrooks
 */
class EventListenerControllerTest {

    @Test
    void testStore() {
        def sampleEventData = createEmptyEvent()

        EventHandlerFactory.metaClass.'static'.build = {String key -> [performAction:{ EventData data ->
            assert key == "store"
            assert !data.getCorrelationId()
            assert !data.getTopic() // This topic comes from the event attributes map
            assert data.data == [:]

        }]}
        EventListenerController controller = new EventListenerController()
        controller.store("topic", sampleEventData)

    }

    @Test
    void testHandle() {
        def sampleEventData = createEmptyEvent()

        EventHandlerFactory.metaClass.'static'.build = {String key -> [performAction:{ EventData data ->
            assert key == "topic"
            assert !data.getCorrelationId()
            assert !data.getTopic() // This topic comes from the event attributes map
            assert data.data == [:]

        }]}
        EventListenerController controller = new EventListenerController()
        controller.handle("topic", sampleEventData)
    }

    static Map createEmptyEvent() {
        String json = "{}"
        String encoded = json.bytes.encodeBase64().toString()
        ["message": [data: encoded, attributes: [:]]]
    }


}
