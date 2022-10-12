Feature: Unit crud endpoints.
  An user of the endpoints is able to create, update, get and delete Units

  Background:
    * url baseUrl

  Scenario: Get all Units
    Given path 'unit'
    When method GET
    Then status 202
    And match response == [{"id":1001,"symbol":"°C"},{"id":1002,"symbol":"PPM"}]

  Scenario: Create a Unit
    Given path 'unit'
    And request {"symbol": "testUnit1"}
    When method POST
    Then status 202

  Scenario: Get a Unit
    Given path 'unit'
    And param id = 1
    When method GET
    Then status 202
    And match response == {"id":1,"symbol":"testUnit1"}

  Scenario: Update a Unit
    Given path 'unit'
    And request {"id":1, "symbol": "testUnit11"}
    When method PUT
    Then status 202

    Given path 'unit'
    And param id = 1
    When method GET
    Then status 202
    And match response == {"id":1,"symbol":"testUnit11"}

  Scenario: Delete a Unit
    Given path 'unit'
    And param id = 1
    When method DELETE
    Then status 202

    Given path 'unit'
    And param id = 1
    When method GET
    Then status 404
