package com.kolosg.Jira.testautomaiton.features.tests;


import com.kolosg.Jira.testautomation.features.Login;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class JiraLoginTest {

    private Login login;
    private final String loginTestData = "/login_test_data.csv";

    @BeforeEach
    void setUp() {
        login = new Login(Util.createDriver("Chrome"));
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void happyPassLoginTest() {
        login.happyPassLogin();
        boolean loginResult = login.validateLogin();
        Assertions.assertTrue(loginResult);
    }

    @Test
    void emptyCredentialsLoginTest() {
        login.loginAttempt("", "");
        login.waitForSEC(Integer.parseInt(Util.getEnvironmentVariable("waiting_seconds")));
        login.loginValidation();
        login.waitForSEC(Integer.parseInt(Util.getEnvironmentVariable("waiting_seconds")));
        Assertions.assertEquals(login.getValidationURL(), login.getDriver().getCurrentUrl());
    }

    @Test
    void CAPTCHAAppearanceTest() {
        String result = login.getCAPTCHA();
        Assertions.assertTrue(result.contains("CAPTCHA"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = loginTestData, numLinesToSkip = 1)
    void failingLoginTest(String username, String password, boolean expectedResult) {
        login.loginAttempt(username, password);
        login.waitForSEC(Integer.parseInt(Util.getEnvironmentVariable("waiting_seconds")));
        login.loginValidation();
        login.waitForSEC(Integer.parseInt(Util.getEnvironmentVariable("waiting_seconds")));
        Assertions.assertEquals(login.getValidationURL(), login.getDriver().getCurrentUrl());
    }

    }
