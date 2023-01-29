Feature: Verify PET API Module

  @petstoretests
  Scenario Outline: Run the Addpet
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
    Examples:
      | id | name  | photourl | status  | categoryid | categoryname |
      | 0  | Bella | dsadf    | confirm | 5          | kumar        |


  @petstoretests
  Scenario Outline: Upload image to pet
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
    Given Send post "uploadimagetopet" request with Image "src/test/resources/utils/doggie.jpg"
    And We Validate the response code and response of Upload image
    Examples:
      | id | name | photourl | status  | categoryid | categoryname |
      | 0  | Luna | dsadf    | pending | 5          | kumar        |


  @petstoretests
  Scenario Outline: Update an Exisiting pet
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
    And We Update the request of Pet
      | updated_name   | <updated_name>   |
      | updated_status | <updated_status> |
    And Send put "Addpet" request
    And We Validate the response code and response in AddPet response

    Examples:
      | id | name | photourl | status  | categoryid | categoryname | updated_name | updated_status |
      | 0  | Milo | dsadf    | pending | 5          | kumar        | Lucy         | sold           |


  @petstoretests
  Scenario: Find the pet by status
    When Send get "findbystatus" request with Parameter name as "status" and Parameter Value as "pending"
    And We Validate the response code and response in find by pet response
    When Send get "findbystatus" request with Parameter name as "status" and Parameter Value as "sold"
    And We Validate the response code and response in find by pet response
    When Send get "findbystatus" request with Parameter name as "status" and Parameter Value as "available"
    And We Validate the response code and response in find by pet response


  @petstoretests
  Scenario: Find the pet by id
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
    And Send get "findpetbyid" request with Petid
    Then We Validate the response code and response in AddPet response
    Examples:
      | id | name  | photourl | status  | categoryid | categoryname |
      | 0  | Bella | dsadf    | confirm | 5          | kumar        |

  @petstoretests
  Scenario: Delete the pet by id
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
    And Send delete "deletepetbyid" request with Petid
    Then We Validate the response code and response in Delete pet response
    And Send get "findpetbyid" request with Petid
    Then We Validate the Record Not found response code and response in Find pet by id response
    Examples:
      | id | name  | photourl | status  | categoryid | categoryname |
      | 0  | Bella | dsadf    | confirm | 5          | kumar        |