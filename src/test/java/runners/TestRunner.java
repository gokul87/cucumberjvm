package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import managers.FileReaderManager;
import com.cucumber.listener.Reporter;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue={"stepDefinitions"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
)
public class TestRunner {

    @AfterClass
    public static void writeExtentReport() throws Exception {
        Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigFileReader().getReportConfigPath()));
    }
}
