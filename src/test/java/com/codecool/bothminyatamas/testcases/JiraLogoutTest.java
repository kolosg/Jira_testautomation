package com.codecool.bothminyatamas.testcases;

import com.codecool.bothminyatamas.javaPOM.Logout;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JiraLogoutTest extends BaseTest{
    private Logout logout = new Logout(driver, wait);
    private JiraLogin login = new JiraLogin(driver);



    @Test
    void logoutHappyPassTest(){
        login.loginAttempt(Util.USERNAME,Util.PASSWORD);
        logout.logout();
        boolean amILoggedOut = logout.isLoggedOut(driver);
        Assertions.assertTrue(amILoggedOut);
    }
}
