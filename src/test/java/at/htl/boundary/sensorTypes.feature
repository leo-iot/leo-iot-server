Feature: SensorType crud endpoints.
  An user of the endpoints is able to create, update, get and delete SensorTypes

  Background:
    * url baseUrl

  Scenario: Get all SensorTypes
    Given path 'sensortype'
    When method GET
    Then status 202
    And match response == [{"id":1001,"name":"Temperature","unit":{"id":1001,"symbol":"°C"}},{"id":1002,"name":"Co2","unit":{"id":1002,"symbol":"PPM"}}]

  Scenario: Create a SensorType
    Given path 'sensortype'
    And request {"name": "testSensorType1","unit": {"id": 1001}}
    When method POST
    Then status 202

  Scenario: Get a SensorType
    Given path 'sensortype'
    And param id = 1
    When method GET
    Then status 202
    And match response == {"id":1,"name":"testSensorType1","unit":{"id":1001,"symbol":"°C"}}

  Scenario: Update a SensorType
    Given path 'sensortype'
    And request {"id":1,"name":"testSensorType11","unit":{"id":1002}}
    When method PUT
    Then status 202

    Given path 'sensortype'
    And param id = 1
    When method GET
    Then status 202
    And match response == {"id":1,"name":"testSensorType11","unit":{"id":1002,"symbol":"PPM"}}

  Scenario: Delete a SensorType
    Given path 'sensortype'
    And param id = 1
    When method DELETE
    Then status 202

    Given path 'sensortype'
    And param id = 1
    When method GET
    Then status 404
