package com.trevorism.event.handler

import com.trevorism.event.model.EventData

/**
 * @author tbrooks
 */
interface EventHandler {

    void performAction(EventData event)
}