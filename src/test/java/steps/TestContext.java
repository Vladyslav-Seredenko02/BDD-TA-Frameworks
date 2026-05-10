package steps;

import org.openqa.selenium.WebDriver;
import pages.DraftPage;
import pages.InboxPage;
import pages.LoginPage;
import services.MailService;

public class TestContext {

    public WebDriver driver;
    public LoginPage loginPage;
    public InboxPage inboxPage;
    public DraftPage draftPage;
    public MailService mailService;
}
