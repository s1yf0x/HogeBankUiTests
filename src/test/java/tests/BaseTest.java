package tests;

import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.DriverFactory;
import utilities.DriverManager;
import utilities.Helper;

public abstract class BaseTest {

    Configuration config = new Configuration();
    DriverManager driverManager = DriverFactory.valueOf(config.getDriver()).getDriverManager();


    @BeforeMethod(alwaysRun = true)
    protected void setup() {
    }

    @AfterMethod(alwaysRun = true)
    protected void cleanup() {
        driverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return driverManager.getDriver();
    }
}
