
@tag
Feature: ErrorValidation
  I want to use this template for my feature file



  @Errorvalidation
  Scenario Outline: Error incorrect mesage
    Given I Landed on Ecommerce Page
    When Logged in with username <name> and password<password>
    Then "Incorrect email or password." message is Displayed

    Examples: 
      | name  								| 	password 				| 
      | balukittu20@gmail.com  |   Haswik@1234			| 
  
