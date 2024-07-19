
@tag
Feature: Purchase the Order from Ecommerce website
  I want to use this template for my feature file

 Background:
 Given I Landed on Ecommerce Page

  @Regression
  Scenario Outline: Positvive Test of Submitting order
    Given Logged in with username <name> and password<password>
    When I add product <productname> to cart
    And Checkout <productname> and Submit the order
    Then "THANKYOU FOR THE ORDER." message is Displed on ConformationPage

    Examples: 
      | name  								| 	password 				| productname |
      | balukittu50@gmail.com |   Haswik@123		| ZARA COAT 3 |
  
