package com.trevorism.event

/**
 * @author tbrooks
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def contextRootContent
def pingContent

Given(~/^the listen application is alive$/) { ->
    try{
        new URL("https://listen-dot-trevorism-eventhub.appspot.com/ping").text
    }
    catch (Exception ignored){
        Thread.sleep(10000)
        new URL("https://listen-dot-trevorism-eventhub.appspot.com/ping").text
    }
}

When(~/^I navigate to "([^"]*)"$/) { String url ->
    contextRootContent = new URL(url).text
}

Then(~/^then a link to the help page is displayed$/) { ->
    assert contextRootContent
    assert contextRootContent.contains("/help")
}

When(~/^I ping the application deployed to "([^"]*)"$/) { String url ->
    pingContent = new URL("${url}/ping").text
}

Then(~/^pong is returned, to indicate the service is alive$/) { ->
    assert pingContent == "pong"
}