package com.kolosg.Jira.testautomation.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Util {

    private static Random random = new Random();

    public final static String USERNAME = getEnvironmentVariable("jira_username");
    public final static String PASSWORD = getEnvironmentVariable("jira_password");
    public final static String BASE_URL = "https://jira.codecool.codecanvas.hu";
    public static final String GRID_URL = System.getenv("grid_url");
    public static final String BROWSER = System.getenv("browser");

    //method simply creates the given webdriver
    public static WebDriver createDriver() throws MalformedURLException {
        String fullGridUrl = GRID_URL.replace("{PASSWORD}", PASSWORD);
        WebDriver driver;
        MutableCapabilities options = null;
        if ("chrome".equals(BROWSER)) {
            options = new ChromeOptions();
        } else if ("firefox".equals(BROWSER)) {
            options = new FirefoxOptions();
        }
        driver = new RemoteWebDriver(new URL(fullGridUrl), options);
        return driver;
    }

    //method to get pre defined environment variables by key
    public static String getEnvironmentVariable(String variableName) {
        String variable = System.getProperty(variableName.toUpperCase());
        if (variable == null) {
            throw new IllegalArgumentException("Environment variable not found!");
        }
        return variable;
    }

    public static void navigateToURL(WebDriver driver,  String URL) {
        driver.get(URL);
    }

    public static void openNewTab(WebDriver driver) {
        ((JavascriptExecutor)driver).executeScript("window.open()");
    }

    public static int generateRandomNumberInRange(int range) {
        return random.nextInt(range);
    }

    public static String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) {
            return text.substring(0, index).trim();
        } else {
            return text;
        }
    }

    /*
    //use this method in need to define the driver properties (driverpath + driverproperty)
    public static void setDriverPath() {
        String driverProperty = getEnvironmentVariable("driver_property");
        String driverPath = getEnvironmentVariable("driver_path");
        System.setProperty(driverProperty, driverPath);
    }
    */

}
