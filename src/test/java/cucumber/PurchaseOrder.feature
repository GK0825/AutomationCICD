@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add productname <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  					 | password    | productName |
      | gk2026@gmail.com | RSa@2025    | ZARA COAT 3 | 
