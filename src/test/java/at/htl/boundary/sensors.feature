Feature: Sensor crud endpoints.
  An user of the endpoints is able to create, update, get and delete Sensors

  Background:
    * url baseUrl

  Scenario: Get all Sensor
    Given path 'sensor'
    When method GET
    Then status 202
    And match response == [{"id":1001,"sensorType":{"id":1001,"name":"Temperature","unit":{"id":1001,"symbol":"°C"}},"thing":{"id":1001,"location":{"id":1003,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e581"},"thing_name":"leobox-e581"}},{"id":1002,"sensorType":{"id":1002,"name":"Co2","unit":{"id":1002,"symbol":"PPM"}},"thing":{"id":1001,"location":{"id":1003,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e581"},"thing_name":"leobox-e581"}},{"id":1003,"sensorType":{"id":1001,"name":"Temperature","unit":{"id":1001,"symbol":"°C"}},"thing":{"id":1002,"location":{"id":1004,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e582"},"thing_name":"leobox-e582"}},{"id":1004,"sensorType":{"id":1002,"name":"Co2","unit":{"id":1002,"symbol":"PPM"}},"thing":{"id":1002,"location":{"id":1004,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e582"},"thing_name":"leobox-e582"}}]

  Scenario: Create a Sensor
    Given path 'sensor'
    And request {"thing": {"id": 1001},"sensorType": {"id": 1001}}
    When method POST
    Then status 202

  Scenario: Get a Sensor
    Given path 'sensor'
    And param id = 1
    When method GET
    Then status 202
    And match response == {"id":1,"sensorType":{"id":1001,"name":"Temperature","unit":{"id":1001,"symbol":"°C"}},"thing":{"id":1001,"location":{"id":1003,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e581"},"thing_name":"leobox-e581"}}

  Scenario: Update a Sensor
    Given path 'sensor'
    And request {"id":1, "thing": {"id": 1002},"sensorType": {"id": 1002}}
    When method PUT
    Then status 202

    Given path 'sensor'
    And param id = 1
    When method GET
    Then status 202
    And match response == {"id":1,"sensorType":{"id":1002,"name":"Co2","unit":{"id":1002,"symbol":"PPM"}},"thing":{"id":1002,"location":{"id":1004,"location":{"id":1002,"location":{"id":1001,"location_name":"HTL Leonding"},"location_name":"eg"},"location_name":"e582"},"thing_name":"leobox-e582"}}

  Scenario: Delete a Sensor
    Given path 'sensor'
    And param id = 1
    When method DELETE
    Then status 202

    Given path 'sensor'
    And param id = 1
    When method GET
    Then status 404
