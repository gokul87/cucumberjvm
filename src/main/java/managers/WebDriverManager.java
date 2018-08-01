package managers;

import enums.BrowserType;
import enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

import static enums.BrowserType.CHROME;
import static enums.BrowserType.FIREFOX;
import static enums.BrowserType.IEXPLORER;

public class WebDriverManager {

    private WebDriver driver;
    private static BrowserType browserType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public WebDriverManager() throws Exception {
        browserType = FileReaderManager.getInstance().getConfigFileReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigFileReader().getEnvironment();
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver(){
        switch(environmentType) {
            case LOCAL: driver = createLocalDriver();
                 break;
            case CLOUD: driver = createRemoteDriver();
                 break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver(){
        throw new RuntimeException("Cloud driver not set up");
    }

    private WebDriver createLocalDriver(){
        switch(browserType) {
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigFileReader().getDriverPath());
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case IEXPLORER:
                driver = new InternetExplorerDriver();
                break;
        }

        if(FileReaderManager.getInstance().getConfigFileReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigFileReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
