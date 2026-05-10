Feature: As a Ukr.net user
  I want to have access to email functionality
  So that I can login, manage drafts and send emails

  Background:
    Given the browser is opened and login page is loaded
    And the user logs in with valid credentials


  Scenario: UC-1 User successfully logs in with valid credentials
    Then the current URL should match the inbox page URL

  Scenario Outline: UC-2 User saves email as draft with different data
    When the user creates a new message
    And fills in the email form with "<recipient>", "<subject>" and "<body>"
    And saves it as draft
    Then the draft should be visible on the drafts page
    And the draft should contain correct "<recipient>", "<subject>" and "<body>"
    And the user cancels the mail

    Examples:
      | recipient       | subject              | body                   |
      | test@ukr.net    | Test subject         | Test body text         |
      | another@ukr.net | Another test subject | Another test body text |

  Scenario: UC-3 User sends email from drafts
    When the user creates a new message
    And fills in the email form with default recipient, subject and body
    And saves it as draft
    And the user opens the draft and sends it
    Then the drafts page should be empty
    And the email should appear on the sent page
