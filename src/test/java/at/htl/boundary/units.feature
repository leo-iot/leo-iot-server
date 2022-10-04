Feature: Unit crud endpoints.
  An user of the endpoints is able to create, update, get and delete Things

  Background:
    * url baseUrl

  Scenario: Get all Units
    Given path 'unit'
    When method GET
    Then status 202
    And match response == [{"id":1001,"symbol":"°C"},{"id":1002,"symbol":"PPM"}]
