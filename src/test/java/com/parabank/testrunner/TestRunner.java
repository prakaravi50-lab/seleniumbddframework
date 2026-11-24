package com.parabank.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/com/parabank/features/login.feature",
				glue = "com.parabank.stepdefinitions",
				dryRun = false,
				monochrome = false,
				plugin = {
						"pretty", "html:Reports/report.html",
						"json:Reports/report.json" },
				tags="@smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
