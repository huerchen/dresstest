package com.elsevier;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature/",
        glue = "com.elsevier",
        plugin = { "pretty", "html:target/cucumber-html-report", "json:target/operation.json" }
)

public class FeatureRunner {
}
