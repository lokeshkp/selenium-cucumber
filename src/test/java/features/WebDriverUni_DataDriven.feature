Feature: WebDriver University

  Scenario Outline: Performing all basic operations in WebDriver University with Data
    Given Launch Browser and navigate to webdriveruniversity contact page
    And Enter Contact Us Form with the <FirstName> and <LastName> then submit
    Then Navigate to buttons window and perform actions
    Then Close Browser

    Examples: 
      | FirstName | LastName |
      | RamaSitha | SithaRama|
      | Laxmana   | Urmila   |
      | Krishna   | Urmila   |
      | Hanuma    | Sri Rama |
