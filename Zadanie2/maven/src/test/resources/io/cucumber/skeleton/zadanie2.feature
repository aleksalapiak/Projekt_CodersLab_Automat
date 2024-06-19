Feature: Purchase Hummingbird Printed Sweater

  Scenario: User purchases Hummingbird Printed Sweater with specific parameters
    Given the user is logged in
    When the user searches for "Hummingbird Printed Sweater"
    Then the user selects the product "Hummingbird Printed Sweater"
    And the user selects size "M"
    And the user selects quantity 5
    And the user adds the product to the cart
    And the user proceeds to checkout and is in the shopping cart
    And the user click on proceed to checkout button
    And the user confirms the address
    And the user selects the pickup method pick up in store
    And the user selects the payment option Pay by Check
    And the user places the order with an obligation to pay
    Then the user takes a screenshot of the order confirmation and total amount

