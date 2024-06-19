Feature: New Address

  Scenario Outline: New Address
    Given I am logged
    And I click on Addresses section and I am on the Addresses page
    And I am in Addresses section and I click on button - create new address
    When I enter "<alias>" as alias and enter "<firstname>" as first name and enter "<lastname>" as last name and  enter "<company>" as company and  enter "<address>" as address and  enter "<city>" as city and  enter "<postcode>" as postcode and  enter "<phone>" as phone
    And I click on Save button
    Then New address is created
    Examples:
      | alias      | firstname | lastname | company   | address     | city     | postcode | phone     |
      | CodersLab  | Coders    | Lab      | CodersLab | Sezamkowa 5 | Warszawa | 123456   | 789654321 |
