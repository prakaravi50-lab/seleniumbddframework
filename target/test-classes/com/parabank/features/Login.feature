@Regression
Feature: To validate the login functionality
@smoke
  Scenario: Login the application with valid credentaials
    Given to launch the parabank application url in chrome browser
    And validate the login page is displaying
    When after displayed the login page enter the "test1" and "test123"
    And click the login button
    #Then validate the it is redirect to home page
    #And ensure the page title
    #And logout from the home page
    And close the chrome browser
