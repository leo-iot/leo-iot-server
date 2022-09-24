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

  Scenario: Update name of a Location
    Given path 'location'
    And request { name: "Location1" }
    When method POST
    Then status 202

    Given path 'location'
    And request { "id": 2,  "location_name": "Location11"}
    When method PUT
    Then status 202

    Given path 'location'
    And param id = 2
    When method GET
    Then status 202
    And match response ==  {"id":2,"location_name":"Location11"}

  Scenario: Update parent Location of a Location
    Given path 'location'
    And request { name: "Location2" }
    When method POST
    Then status 202

    Given path 'location'
    And request { name: "Location3", "parentLocationId": 2 }
    When method POST
    Then status 202

    Given path 'location'
    And request { "id": 4,  "location_name": "Location33", "location": {"id": 3}}
    When method PUT
    Then status 202

    Given path 'location'
    And param id = 4
    When method GET
    Then status 202
    And match response == {"id":4,"location":{"id":3,"location_name":"Location2"},"location_name":"Location33"}


