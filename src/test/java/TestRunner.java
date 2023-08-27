import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;


@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"},
    features = {"src/test/resources/features"},
    glue = {"cucumber.stepdefinitions"}
)

@RunWith(Cucumber.class)
public class TestRunner extends AbstractTestNGCucumberTests {

}
