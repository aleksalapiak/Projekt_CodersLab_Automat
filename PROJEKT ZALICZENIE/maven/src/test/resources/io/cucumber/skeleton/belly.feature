Feature: Belly

  Scenario: a few cukes
    Given I have 423652864865 cukes in my belly
    When I wait 1 hour
    Then my belly should growl
