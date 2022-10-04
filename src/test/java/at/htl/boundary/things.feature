Feature: Thing crud endpoints.
  An user of the endpoints is able to create, update, get and delete Things

  Background:
    * url baseUrl

  Scenario: Get all Things
    Given path 'thing'
    When method GET
    Then status 202
    And match response == [{"id":1001,"location":{"id":1003,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e581"},"thing_name":"leobox-e581"},{"id":1002,"location":{"id":1004,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e582"},"thing_name":"leobox-e582"}]

  Scenario: Create a Thing
    Given path 'thing'
    And request {"location": {"id": 1003},"thing_name": "LeoBox1-e59"}
    When method POST
    Then status 202

  Scenario: Get a Thing
    Given path 'thing'
    And param id = 1
    When method GET
    Then status 202
    And match response == {"id":1,"location":{"id":1003,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e581"},"thing_name":"LeoBox1-e59"}

  Scenario: Delete a Thing
    Given path 'thing'
    And param id = 1
    When method DELETE
    Then status 202

    Given path 'thing'
    And param id = 1
    When method GET
    Then status 404

  Scenario: Update a Thing
    Given path 'thing'
    And request {"location": {"id": 1003},"thing_name": "LeoBox1-e59"}
    When method POST
    Then status 202

    Given path 'thing'
    And param id = 2
    When method GET
    Then status 202
    And match response == {"id":2,"location":{"id":1003,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e581"},"thing_name":"LeoBox1-e59"}

    Given path 'thing'
    And request {"id":2,"location":{"id":1004},"thing_name":"LeoBox1-e59"}
    When method PUT
    Then status 202

    Given path 'thing'
    And param id = 2
    When method GET
    Then status 202
    And match response == {"id":2,"location":{"id":1004,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e582"},"thing_name":"LeoBox1-e59"}
