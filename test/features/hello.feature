Feature: A lonely developer can get a hello message from this API

Scenario: Request without parameter should send error
    When I GET /v1/hello
    Then response code should be 400

Scenario: Request with invalid parameter should send error
    When I GET /v1/hello?n=me
    Then response code should be 400

Scenario: Request with valid parameter should send valid greeting
    When I GET /v1/hello?name=Po
    Then response code should be 200
      And response body path $.message should be Hello Po!

Scenario: Request with valid parameter as variable should send valid greeting
    When I GET /v1/hello?name=`NAME_SHIFU`
    Then response code should be 200
      And response body path $.message should be Hello `NAME_SHIFU`!