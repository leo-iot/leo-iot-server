Feature: Location creation endpoint.
  An user of the endpoint is able to create Location

  Background:
    * url baseUrl

  Scenario: Create a Location
    Given path 'location'
    And request { name: "Location1" }
    When method POST
    Then status 202

  Scenario: Get a Location
    Given path 'location'
    And param id = 1
    When method GET
    Then status 202
    And match response ==  {"id":1,"location_name":"Location1"}

  Scenario: Delete a Location
    Given path 'location'
    And param id = 1
    When method DELETE
    Then status 202

    Given path 'location'
    And param id = 1
    When method GET
    Then status 404
