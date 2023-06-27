package FakeRestAPI.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions   (
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:cucumber-html-reports.html",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
        },
        features = "src/test/resources/Features",
        glue =  "FakeRestAPI/step_def",
        dryRun = false,
        tags = "@wip5"

)
public class CukeRunner {
}
