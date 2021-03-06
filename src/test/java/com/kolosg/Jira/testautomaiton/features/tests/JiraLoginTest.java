package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.net.MalformedURLException;

public class JiraLoginTest {

    private JiraLogin login;

    private final String loginTestData = "/login_test_data.csv";

    @BeforeEach
    void setUp() throws MalformedURLException {
        login = new JiraLogin(Util.createDriver());
        Util.navigateToURL(login.getDriver(),Util.BASE_URL);
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void happyPassLoginTest() {
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
        login.loginValidation();
        Assertions.assertEquals(login.getValidationURL(), login.getDriver().getCurrentUrl());
    }

    @Test
    void emptyCredentialsLoginTest() {
        login.loginAttempt("", "");
        login.loginValidation();
        Assertions.assertEquals(login.getValidationURL(), login.getDriver().getCurrentUrl());
    }

    @ParameterizedTest
    @CsvFileSource(resources = loginTestData, numLinesToSkip = 1)
    void failingLoginTest(String username, String password, boolean expectedResult) {
        login.loginAttempt(username, password);
        login.loginValidation();
        Assertions.assertEquals(login.getValidationURL(), login.getDriver().getCurrentUrl());
    }

    /*
    @Test
    void CAPTCHAAppearanceTest() {
        Util.openNewTab(login.getDriver());
        Util.openNewTab(login.getDriver());
        ArrayList<String> tabs = new ArrayList<String>(login.getDriver().getWindowHandles());
        login.getCAPTCHA();
        login.getDriver().switchTo().window(tabs.get(1));
        Util.navigateToURL(login.getDriver(), BASE_URL);
        login.getCAPTCHA();
        login.getDriver().switchTo().window(tabs.get(2));
        Util.navigateToURL(login.getDriver(), BASE_URL);
        login.getCAPTCHA();
        String result = login.getErrorMessage();
        System.out.println(result);
        Assertions.assertTrue(result.contains("CAPTCHA"));
    }*/
}
