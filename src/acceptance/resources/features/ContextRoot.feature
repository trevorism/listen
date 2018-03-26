Feature: Context Root of Listen
  In order to use the listen API, it must be available

  Scenario: ContextRoot on app engine
    Given the listen application is alive
    When I navigate to "https://listen-dot-trevorism-eventhub.appspot.com"
    Then then a link to the help page is displayed

  Scenario: Ping on app engine
    Given the listen application is alive
    When I ping the application deployed to "https://listen-dot-trevorism-eventhub.appspot.com"
    Then pong is returned, to indicate the service is alive
