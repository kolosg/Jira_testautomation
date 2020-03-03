package com.kolosg.Jira.testautomaiton.features.tests;


import com.kolosg.Jira.testautomation.features.Login;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class JiraLoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login(Util.createDriver("Chrome"));
        login.getDriver().get(Util.getEnvironmentVariable("base_url"));
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }



}
