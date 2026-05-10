package steps;

import decorators.LoggingMailService;
import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.DraftPage;
import pages.InboxPage;
import pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseSteps {
    private static final Logger log = LogManager.getLogger(BaseSteps.class);
    protected final TestContext testContext;

    public BaseSteps(TestContext testcontext) {
       this.testContext = testcontext;
    }

    @Given("the browser is opened and login page is loaded")
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        log.info("=== Starting tests on browser: {} ===", browser);
        DriverFactory.initializeDriver(browser);
        testContext.driver = DriverFactory.getDriver();
        testContext.loginPage = new LoginPage(testContext.driver);
        testContext.inboxPage = new InboxPage(testContext.driver);
        testContext.draftPage = new DraftPage(testContext.driver);
        testContext.mailService = new LoggingMailService(testContext.inboxPage);

        testContext.loginPage.openPage();
    }

    @And("the user logs in with valid credentials")
    public void userLogsIn() {
        testContext.loginPage.enterUsername();
        testContext.loginPage.enterPassword();
        testContext.loginPage.clickLoginBtn();
        testContext.inboxPage.waitTillPageLoaded(testContext.inboxPage.getInboxPageUrl());
    }


    @After
    public void closeBrowser() {
        log.info("=== Closing browser ===");
        DriverFactory.tearDown();
    }
}