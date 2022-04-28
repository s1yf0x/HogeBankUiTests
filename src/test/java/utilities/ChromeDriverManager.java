package utilities;

import configuration.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class ChromeDriverManager extends DriverManager {

    Configuration config = new Configuration();

    protected WebDriver createDriver() {
        WebDriverManager.getInstance(CHROME).setup();

        WebDriver driver = new ChromeDriver(getChromeOptions());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--verbose");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--lang=ja");
        if (config.getHeadlessExecution()) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }
}
