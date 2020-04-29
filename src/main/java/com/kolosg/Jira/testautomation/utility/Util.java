package com.kolosg.Jira.testautomation.utility;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Util {

    private static final Random random = new Random();

    public final static String USERNAME = System.getenv("DEFAULT_USERNAME");
    public final static String PASSWORD = System.getenv("DEFAULT_PW");
    public final static String SELENIUM_GRID_PASSWORD = System.getenv("SELENIUM_GRID_PASSWORD");
    public final static String BASE_URL = "https://jira.codecool.codecanvas.hu";
    public final static String GRID_URL = System.getenv("GRID_URL");
    public final static String BROWSER = System.getenv("BROWSER");

    public static RemoteWebDriver createDriver() throws MalformedURLException {
        String fullGridUrl = GRID_URL.replace("{PASSWORD}", SELENIUM_GRID_PASSWORD);
        MutableCapabilities options = null;
        if ("chrome".equals(BROWSER)) {
            options = new ChromeOptions();
        } else if ("firefox".equals(BROWSER)) {
            options = new FirefoxOptions();
        }
        return new RemoteWebDriver(new URL(fullGridUrl), options);
    }

    public static void navigateToURL(WebDriver driver,  String URL) {
        driver.get(URL);
    }

    public static int generateRandomNumberInRange(int range) {
        return random.nextInt(range);
    }
}
