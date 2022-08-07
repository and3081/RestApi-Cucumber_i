package ru.vasyukov;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Стартовый класс (листенер задан в спеках)
 * В tags можно задавать выборочное выполнение тестов
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/ru.vasyukov/features",
        glue = {"ru.vasyukov.stepDefinitions"},
        plugin={"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
//                "json:target/cucumber.json", "html:target/test-output"},
        tags = "@ReqresLogin"
)
public class RunnerTest {}
