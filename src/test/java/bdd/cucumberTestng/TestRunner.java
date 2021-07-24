package test.java.bdd.cucumberTestng;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/bdd/cucumberTestng/features/"
        ,glue = {"test/java/bdd/cucumberTestng/stepdefinitions"}
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        ,tags = "@FunctionalTest"
        ,monochrome = true
        ,dryRun = true
)
public class TestRunner
{}