Feature: Dresses shopping

  Scenario: Buy summer dresses
    Given I am on the home page
    When  I go to "Dresses" page and select "Summer Dresses"
    And  I add "Long printed dress" to cart
    And   I add product to cart
      | description                          | quantity | size    | color    |
      | Printed chiffon knee length dress | 2         | M        | Green  |
    And I open cart
    Then I should see total price is "$63.78"
    Then I go to Sign in section when try to check out