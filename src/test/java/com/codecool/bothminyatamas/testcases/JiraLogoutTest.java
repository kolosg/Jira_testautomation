package com.codecool.bothminyatamas.testcases;

import com.codecool.bothminyatamas.javaPOM.Logout;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JiraLogoutTest extends BaseTest{
    private Logout logout = new Logout(driver, wait);


    @Test
    void logoutHappyPassTest(){
        Util.navigateToBase(driver);
        logout.login();
        logout.logout();
        boolean amILoggedOut = logout.isLoggedOut(driver);
        Assertions.assertTrue(amILoggedOut);
    }
}
