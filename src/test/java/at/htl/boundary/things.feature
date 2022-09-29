Feature: Thing crud endpoints.
  An user of the endpoints is able to create, update, get and delete Things

  Background:
    * url baseUrl

  Scenario: Create a Thing
    Given path 'thing'
    And request { thing_name: "LeoBox1-e59" }
    When method POST
    Then status 202

  Scenario: Get a Thing
    Given path 'thing'
    And param id = 1
    When method GET
    Then status 202
    And match response == {"id":1,"thing_name":"LeoBox1-e59"}

  Scenario: Delete a Thing
    Given path 'thing'
    And param id = 1
    When method DELETE
    Then status 202

    Given path 'thing'
    And param id = 1
    When method GET
    Then status 404
