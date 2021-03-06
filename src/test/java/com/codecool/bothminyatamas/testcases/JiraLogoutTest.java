package com.codecool.bothminyatamas.testcases;

import com.codecool.bothminyatamas.javaPOM.Logout;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class JiraLogoutTest extends BaseTest{
    private Logout logout = new Logout(driver, wait);
    private JiraLogin login = new JiraLogin(driver);

    public JiraLogoutTest() throws MalformedURLException {
    }

    @Test
    void logoutHappyPassTest(){
        Util.navigateToURL(driver, Util.BASE_URL);
        login.loginAttempt(Util.USERNAME,Util.PASSWORD);
        login.waitForSuccessfulLogin();
        logout.logout();
        boolean amILoggedOut = logout.isLoggedOut(driver);
        Assertions.assertTrue(amILoggedOut);
    }
}
