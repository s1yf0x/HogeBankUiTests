package pageObject;

import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

public class PageObject {
    protected WebDriver driver;
    protected Configuration config = new Configuration();
    protected Helper helper = new Helper();

    public PageObject(WebDriver driver) {
        this.driver = driver;

    }
}