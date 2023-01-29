Feature: Verify User API Module

  @petstoretests
  Scenario: Run the Create with List for User
    Given Prepare update Add User request for below data
      | id | username  | firstName | lastName | email                 | password | phone      | userStatus |
      | 0  | Testuser  | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |
      | 0  | Testuser1 | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |
    When Send post "createUserWithList" request
    Then We Validate the response code and response in createUserWithList response


  @petstoretests
  Scenario Outline: Run the Create a new user
    Given Prepare update Create user request for below data
      | id         | <id>         |
      | username   | <username>   |
      | firstName  | <firstName>  |
      | lastName   | <lastName>   |
      | email      | <email>      |
      | password   | <password>   |
      | phone      | <phone>      |
      | userStatus | <userStatus> |
    When Send post "createuser" request
    Then We Validate the response code and response in create User new User response
    Examples:
      | id | username  | firstName | lastName | email                 | password | phone      | userStatus |
      | 0  | Leaseplan | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |


  @petstoretests
  Scenario: Run the Create with Array for User
    Given Prepare update Add User request for below data
      | id | username  | firstName | lastName | email                 | password | phone      | userStatus |
      | 0  | Testuser  | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |
      | 0  | Testuser1 | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |
    When Send post "createWithArray" request
    Then We Validate the response code and response in createUserWithList response

  @petstoretests
  Scenario: Run the User API through Find by Username
    When Send get "findbyusername" request with username "Testuser"
    Then We Validate the response code and response in createUserWithList response
    When Send get "findbyusername" request with username "sampleuser"
    Then We Validate the response code and response Where user name Found response


  @petstoretests
  Scenario: Run the Update the User API for User
    Given Prepare update Create user request for below data
      | id         | <id>         |
      | username   | <username>   |
      | firstName  | <firstName>  |
      | lastName   | <lastName>   |
      | email      | <email>      |
      | password   | <password>   |
      | phone      | <phone>      |
      | userStatus | <userStatus> |
    And   Send get "logindetails" request with username and password
    Then We Validate the login Successful
    When Send put "updateusername" request with username "Testuser"
    Then We Validate the response code and response in createUserWithList response
    Examples:
      | id | username  | firstName | lastName | email                 | password | phone      | userStatus |
      | 0  | Leaseplan | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |

  @petstoretests
  Scenario: Run the Login with Username and Password
    Given Prepare update Create user request for below data
      | id         | <id>         |
      | username   | <username>   |
      | firstName  | <firstName>  |
      | lastName   | <lastName>   |
      | email      | <email>      |
      | password   | <password>   |
      | phone      | <phone>      |
      | userStatus | <userStatus> |
    When Send post "createuser" request
    Then We Validate the response code and response in create User new User response
    And   Send get "logindetails" request with username and password
    Then We Validate the login Successful
    Examples:
      | id | username  | firstName | lastName | email                 | password | phone      | userStatus |
      | 0  | Leaseplan | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |

  @petstoretests
  Scenario: Run the Login with Username and Password and Logout
    Given Prepare update Create user request for below data
      | id         | <id>         |
      | username   | <username>   |
      | firstName  | <firstName>  |
      | lastName   | <lastName>   |
      | email      | <email>      |
      | password   | <password>   |
      | phone      | <phone>      |
      | userStatus | <userStatus> |
    When Send post "createuser" request
    Then We Validate the response code and response in create User new User response
    And   Send get "logindetails" request with username and password
    Then We Validate the login Successful
    And Send get "logout" request
    And We Validate the logout Successful
    Examples:
      | id | username  | firstName | lastName | email                 | password | phone      | userStatus |
      | 0  | Leaseplan | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |


  @petstoretests
  Scenario: Run the Login with Username and Password and Logout
    Given Prepare update Create user request for below data
      | id         | <id>         |
      | username   | <username>   |
      | firstName  | <firstName>  |
      | lastName   | <lastName>   |
      | email      | <email>      |
      | password   | <password>   |
      | phone      | <phone>      |
      | userStatus | <userStatus> |
    When Send post "createuser" request
    Then We Validate the response code and response in create User new User response
    And   Send get "logindetails" request with username and password
    Then We Validate the login Successful
    And Send delete "deleteruser" request for Username
    Then We Validate the response code and response in createUserWithList response
    When Send get "findbyusername" request with username "Leaseplan1"
    Then We Validate the response code and response Where user name Found response
    And Send get "logout" request
    And We Validate the logout Successful
    Examples:
      | id | username   | firstName | lastName | email                 | password | phone      | userStatus |
      | 0  | Leaseplan1 | Selenium  | BDD      | seleniumbdd@gmail.com | abc@123  | 0767676776 | 1          |
