package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/WebDriverUni.feature", 
				glue="stepDef",dryRun = false,
				monochrome = true,
				plugin = { "pretty", "html:target/cucumber-html-report"})
public class MyRunner {

}