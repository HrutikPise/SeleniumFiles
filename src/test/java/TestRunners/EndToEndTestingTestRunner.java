package TestRunners;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/Feature/EndToEndTestingCucumber.feature",
		glue={"StepDefinationsDemos/EndToEndTestingCucumberRest"},
		plugin= {"json:target/jsonReports/first.json"}
		)
public class EndToEndTestingTestRunner {
//tags="@AfterClass"
}
