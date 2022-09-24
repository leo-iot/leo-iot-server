Feature: Thing crud endpoints.
  An user of the endpoints is able to create, update, get and delete Things

  Background:
    * url baseUrl

  Scenario: Create a Thing
    Given path 'thing'
    And request { name: "LeoBox1-e59" }
    When method POST
    Then status 202
