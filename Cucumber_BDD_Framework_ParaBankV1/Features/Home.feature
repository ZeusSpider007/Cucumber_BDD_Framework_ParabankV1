Feature: Homepage

  @regression
  Scenario: Successful verification of homepage header Links
    Given the user is on homepage
    Then the homepage should display correct header links

  @regression
  Scenario: Successful verification of Parabank logo and quick links
    Given the user is on homepage
    And the homepage should display the parabank logo
    And the quicklinks should display and linked correctly

  @sanity
  Scenario: Successful verification of Footer panel and links
    Given the user is on homepage
    And the footer panel should be displayed
    And all the footer links should be present
