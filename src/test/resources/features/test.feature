Feature: Test demoblaze

  Scenario Outline: Purchase a product
    Given the user navigates to the home page
    When the user goes to a product "<productName>"
    And the user adds the product to the cart
    And the user goes to the cart
    And place order
    And complete the data of payment with name "<name>", card number "<cardNumber>", month "<month>", and year "<year>"
    Then the user verify the purchase

    Examples:
      | productName       | name          | cardNumber           | month | year |
      | Samsung galaxy s6 | Wilson Tellez | 4111-1111-1111-1111  | 12    | 26   |
      | Nokia lumia 1520  | Wilson Tellez | 4111-1111-1111-1111  | 12    | 26   |



  Scenario Outline: Add and delete product
    Given the user navigates to the home page
    When the user goes to a product "<productName>"
    And the user adds the product to the cart
    And the user goes to the cart
    And the user verifies that the product "<productName>" is displayed in the cart
    Then the user deletes the item "<productName>" from the cart
    And the product "<productName>" is removed from the cart

    Examples:
      | productName          |
      | Nokia lumia 1520     |
      | Samsung galaxy s6    |


  Scenario Outline: Verify product price in cart
    Given the user navigates to the home page
    When the user verify the price in home of "<productName>"
    And the user goes to a product "<productName>"
    And the user captures the price of the product
    And the user adds the product to the cart
    And the user goes to the cart
    Then the user verifies that the product price in the cart matches the captured price

    Examples:
      | productName          |
      | Nokia lumia 1520     |
      | Samsung galaxy s6    |


  Scenario: Attempt to place an order with an empty cart
    Given the user navigates to the home page
    And the user goes to the cart
    When the user tries to place an order without adding items
    Then the purchase should not be allowed