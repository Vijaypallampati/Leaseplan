Feature: Verify Store Order API Module

  @debug11
  Scenario Outline: Run the Place Order for pet
    Given Prepare update Add Pet request for below data
      | id           | <id>           |
      | name         | <name>         |
      | photourl     | <photourl>     |
      | status       | <status>       |
      | categoryid   | <categoryid>   |
      | categoryname | <categoryname> |
    When Update Tags to Add Pet request with below data
      | id | name |
      | 1  | dog  |
    When Send post "Addpet" request
    Then We Validate the response code and response in AddPet response
    And Prepare update Add Order request for below data
      | id          | <id>          |
      | quantity    | <quantity>    |
      | Orderstatus | <Orderstatus> |
    And Send post "placeorder" request
    Then We Validate the response code and response in Order Pet response
    Examples:
      | id | name  | photourl | status  | categoryid | categoryname | quantity | Orderstatus |
      | 0  | Bella | Doggie   | confirm | 5          | kumar        | 3        | placed      |


  @debug11
  Scenario Outline: Run the find by Order id for pet
    Given Prepare update Add Pet request for below data
      | id           | <id>           |
      | name         | <name>         |
      | photourl     | <photourl>     |
      | status       | <status>       |
      | categoryid   | <categoryid>   |
      | categoryname | <categoryname> |
    When Update Tags to Add Pet request with below data
      | id | name |
      | 1  | dog  |
    When Send post "Addpet" request
    Then We Validate the response code and response in AddPet response
    And Prepare update Add Order request for below data
      | id          | <id>          |
      | quantity    | <quantity>    |
      | Orderstatus | <Orderstatus> |
    And Send post "placeorder" request
    Then We Validate the response code and response in Order Pet response
    And Send get "findbyorder" request with Orderid
    Then We Validate the response code and response in Order Pet response
    Examples:
      | id | name  | photourl | status  | categoryid | categoryname | quantity | Orderstatus |
      | 0  | Bella | Doggie   | confirm | 5          | kumar        | 3        | delivered      |


  @debug11
  Scenario Outline: Run the Delete Order id for pet
    Given Prepare update Add Pet request for below data
      | id           | <id>           |
      | name         | <name>         |
      | photourl     | <photourl>     |
      | status       | <status>       |
      | categoryid   | <categoryid>   |
      | categoryname | <categoryname> |
    When Update Tags to Add Pet request with below data
      | id | name |
      | 1  | dog  |
    When Send post "Addpet" request
    Then We Validate the response code and response in AddPet response
    And Prepare update Add Order request for below data
      | id          | <id>          |
      | quantity    | <quantity>    |
      | Orderstatus | <Orderstatus> |
    And Send post "placeorder" request
    Then We Validate the response code and response in Order Pet response
    And Send delete "deleteorder" request with Orderid
    And We Validate the response code and response in Delete Order response
    And Send get "findbyorder" request with Orderid
    Then We Validate the Record Not found response code and response in Find Order by id response
    Examples:
      | id | name  | photourl | status  | categoryid | categoryname | quantity | Orderstatus |
      | 0  | Bella | Doggie   | confirm | 5          | kumar        | 3        | approved      |


  @debug111
  Scenario: Run the Inventory for pet
    When Send get "storeinventory" request
    Then We Validate the response code and response for Inventory response