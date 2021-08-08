Feature: Login Test
  Scenario: Login positive test
    Given Goto Google page "https://www.google.com"
    When The page is loaded
    And Search keyword "gmail"