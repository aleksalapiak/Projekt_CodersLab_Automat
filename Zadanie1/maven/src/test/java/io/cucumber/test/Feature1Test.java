package io.cucumber.test;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/io/cucumber/skeleton/zadanie1.feature",
        glue = "io.cucumber.skeleton"
)
public class Feature1Test {
}
