package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class TestResultEventHandler extends AbstractPingingEventHandler {

    @Override
    String getPingUrl() {
        "https://email-dot-trevorism-gcloud.appspot.com/ping"
    }

    @Override
    String getPostUrl(EventData eventData) {
        'https://email-dot-trevorism-gcloud.appspot.com/mail/'
    }

    @Override
    protected convertEventIntoPostObject(EventData event) {
        def email = [:]
        email["subject"] = "Test for ${event.data.projectName} failed".toString()
        email["recipients"] = ["alerts@trevorism.com"]
        email["body"] = buildMessage(event.data)
        email
    }

    @Override
    void performAction(EventData event) {
        //Only email if the test failed
        if (event.data.passing)
            return
        super.performAction(event)
    }

    private static String buildMessage(def data) {
        String message = """Test failed after ${data.durationMillis} milliseconds.

Feature: ${data.feature}
Scenario: ${data.name}

${data?.given}
${data?.when}
${data?.then}"""

        if(data.errorMessage){
            message += "\\n\\n${data.errorMessage}"
        }
        return message
    }

}
