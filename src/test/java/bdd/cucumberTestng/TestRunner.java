package test.java.bdd.cucumberTestng;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
//
        ,features= { "src/test/resources/features"}
        ,glue = {"test/java/bdd/cucumberTestng/stepdefinitions"}
        ,monochrome = true
        ,dryRun = false
)
public class TestRunner
{}