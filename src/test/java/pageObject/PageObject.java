package pageObject;

import configuration.Configuration;
import org.openqa.selenium.WebDriver;

public class PageObject {
    protected WebDriver driver;
    protected Configuration config = new Configuration();

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }
}