package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;

public class BrowserParameterListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        String browser = context.getCurrentXmlTest().getParameter("browser");
        if (browser != null) {
            System.setProperty("browser", browser);
        }
    }
}