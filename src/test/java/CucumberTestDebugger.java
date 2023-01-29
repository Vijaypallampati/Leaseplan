import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@petstoretests",
        monochrome = true,
        plugin = {"json:target/cucumber-reports/Cucumber.json",
                "de.monochromata.cucumber.report.PrettyReports:target/cucumber-reports/cucumber",
                "pretty"}
)
public class CucumberTestDebugger {
}
