Feature: Cake Manager can list and add cakes

  Background:
    Given the Cake Manager is running

  Scenario: Cake titles returned when requested
    When a list of cake titles is requested
    Then the list of cake titles is returned

  Scenario: Cake list returned when requested
    When a list of cake details is requested
    Then the list of cake details is returned

  Scenario: Cake can be added and returned
    When a new cake is added
    And a request to retrieve the new cake is made
    Then the new cake is returned
