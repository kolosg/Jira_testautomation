package com.codecool.bothminyatamas.testcases;

import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public abstract class BaseTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        driver = Util.createDriver();

        wait = new WebDriverWait(driver, 50);
        driver.manage().window().maximize();
    }

    @AfterAll
    static void TearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
