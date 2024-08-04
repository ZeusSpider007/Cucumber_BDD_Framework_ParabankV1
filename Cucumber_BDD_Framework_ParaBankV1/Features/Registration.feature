Feature: Account Registration

  @regression
  Scenario Outline: Successful Account Registration
    Given the user navigates to Register Account page
    When the user enters the details into below fields
      | firstName      | <firstName>      |
      | lastName       | <lastName>       |
      | address        | <address>        |
      | city           | <city>           |
      | state          | <state>          |
      | zipcode        | <zipcode>        |
      | phonenumber    | <phonenumber>    |
      | SSN            | <SSN>            |
      | username       | <username>       |
      | password       | <password>       |
      | repeatpassword | <repeatpassword> |
    And the user clicks on register
    Then the user account should get created successfully
    Then logout the session

    Examples: 
      | firstName | lastName | address       | city        | state | zipcode | phonenumber | SSN       | username  | password | repeatpassword |
      | Alice     | Johnson  | Crescent Lane | New York    | NY    |   10001 |  2125551234 | XN3B4567C | alicej    | Passw0rd | Passw0rd       |
      | Bob       | Miller   | Elm Street    | Los Angeles | CA    |   90001 |  3235555678 | YN4C7890D | bobmiller | MyS3cret | MyS3cret       |
