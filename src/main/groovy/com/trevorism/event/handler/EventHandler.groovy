package com.trevorism.event.handler

import com.trevorism.event.model.ReceivedEvent

/**
 * @author tbrooks
 */
interface EventHandler {
    void performAction(ReceivedEvent event)
}