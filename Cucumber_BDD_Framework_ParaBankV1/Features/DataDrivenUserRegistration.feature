Feature: Data Driven User Registration

  @regression
  Scenario Outline: Successful Account Registration
    Given the user navigates to Register Account page
    When the user enters the details from the Excel file row <row_index>
    And the user clicks on register
    Then the user account should get created successfully
    Then logout the session

    Examples: 
      | row_index |
      |         3 |
      |         4 |
