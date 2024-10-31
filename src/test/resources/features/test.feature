Feature: Test demoblaze

  Scenario Outline: Purchase a product
    Given the user navigates to the home page
    When the user goes to a product "<productName>"
    And the user adds the product to the cart
    And the user goes to the cart
    And place order
    And complete the data of payment
    Then the user verify the purchase

    Examples:
      | productName          |
      | Samsung galaxy s6    |
      | Nokia lumia 1520     |


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