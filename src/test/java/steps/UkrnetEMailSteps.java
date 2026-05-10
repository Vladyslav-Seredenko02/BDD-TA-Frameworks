package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class UkrnetEMailSteps {
    private static final Logger log = LogManager.getLogger(UkrnetEMailSteps.class);
    protected final TestContext testContext;

    public UkrnetEMailSteps(TestContext testContext) {
        this.testContext = testContext;
    }


    @Then("the current URL should match the inbox page URL")
    public void verifyInboxUrl() {
        Assert.assertEquals(testContext.inboxPage.getCurrentUrl(), testContext.inboxPage.getInboxPageUrl()
                , "Login was`t successful - urls were`t matching");
    }


    @When("the user creates a new message")
    public void UserCreatesANewMessage() {
        testContext.mailService.createMessage();
    }

    @And("fills in the email form with {string}, {string} and {string}")
    public void fillsInTheEmailFormWithAnd(String recipient, String subject, String body) {
        testContext.mailService.fillEmailForm(recipient, subject, body);
    }

    @And("saves it as draft")
    public void savesItAsDraft() {
        testContext.mailService.switchToDrafts();
    }

    @Then("the draft should be visible on the drafts page")
    public void theDraftShouldBeVisibleOnTheDraftsPage() {
        Assert.assertTrue(testContext.draftPage.mailIsDisplayed(), "Mail isn`t displayed on the draft page");
    }

    @And("the draft should contain correct {string}, {string} and {string}")
    public void theDraftShouldContainCorrectAnd(String recipient, String subject, String body) {
        testContext.draftPage.openTheMail();

        Assert.assertEquals(testContext.draftPage.getAddresseeText(), recipient
                , "Addressee does`t match the expected recipient");

        Assert.assertEquals(testContext.draftPage.getEmailTopicText(), subject
                , "Topic does`t match the expected subject");

        Assert.assertEquals(testContext.draftPage.getMailBodyInputText(), body
                , "MailBodyInput does`t match the expected body");

    }

    @And("the user cancels the mail")
    public void theUserCancelsTheMail() {
        testContext.draftPage.cancelTheMail();
    }


    @And("fills in the email form with default recipient, subject and body")
    public void fillsInTheEmailFormWithDefaultRecipientSubjectAndBody() {
        testContext.mailService.fillEmailForm(TestData.RECIPIENT, TestData.SUBJECT, TestData.BODY);
    }


    @And("the user opens the draft and sends it")
    public void theUserOpensTheDraftAndSendsIt() {
        testContext.draftPage.openTheMail();
        testContext.draftPage.sendTheMail();
    }

    @Then("the drafts page should be empty")
    public void theDraftsPageShouldBeEmpty() {
        testContext.mailService.switchToDrafts();
        Assert.assertTrue(testContext.draftPage.isDraftPageClear(), "Draft page still contains the mail");
    }

    @And("the email should appear on the sent page")
    public void theEmailShouldAppearOnTheSentPage() {
        testContext.mailService.switchToSents();
        Assert.assertTrue(testContext.draftPage.mailIsDisplayed(), "Mail isn`t displayed on the sent page");
    }
}