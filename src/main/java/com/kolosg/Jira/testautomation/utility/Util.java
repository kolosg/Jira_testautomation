package com.kolosg.Jira.testautomation.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Util {

    //method simply creates the given webdriver
    public static WebDriver createDriver(String driverType) {
        WebDriver driver;
        if(driverType.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (driverType.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else if (driverType.equalsIgnoreCase("Safari")) {
            driver = new SafariDriver();
        } else if (driverType.equalsIgnoreCase("Opera")) {
            driver = new OperaDriver();
        } else if (driverType.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("No such driver!");
        }
        return driver;
    }

    //method to get pre defined environment variables by key
    public static String getEnvironmentVariable(String variableName) {
        String variable = System.getenv(variableName.toUpperCase());
        if (variable == null) {
            throw new IllegalArgumentException("Environment variable not found!");
        }
        return variable;
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
