package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
class EmailEventHandler extends AbstractPingingEventHandler {

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
        email["subject"] = event.data.subject
        email["recipients"] = constructRecipientsList(event.data.recipients)
        email["body"] = event.data.body
        email
    }

    private List<String> constructRecipientsList(def recipients) {
        if(recipients instanceof String){
            String stringRecipients = recipients as String
            if(recipients.contains(";"))
                return stringRecipients.split(";").collect { it.trim()}
            if(recipients.contains(","))
                return stringRecipients.split(",").collect { it.trim()}
            return stringRecipients.split(" ").collect { it.trim()}
        }
        return recipients
    }
}
