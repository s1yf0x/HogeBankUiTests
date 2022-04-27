package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends PageObject {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, config.getWaitTimeout());

    /**
     * All locators not is best and application implemented so terrible. I would to ask developers add some additional
     * attributes to needed elements for more stable tests
     */
    private final By title = By.xpath("//h1[@class='center']");
    private final By fldUsername = By.xpath("//input[1]");
    private final By fldPassword = By.xpath("//input[@type='password']");
    private final By btnSignup = By.xpath("//button[normalize-space()='SIGNUP']");
    private final By btnLogin = By.xpath("//button[normalize-space()='LOGIN']");

    public LoginPage typeUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldUsername));
        driver.findElement(fldUsername).sendKeys(username);
        return new LoginPage(driver);
    }

    public LoginPage typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldPassword));
        driver.findElement(fldPassword).sendKeys(password);
        return new LoginPage(driver);
    }

    public SignupPage clickSignupBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSignup));
        driver.findElement(btnSignup).click();
        return new SignupPage(driver);
    }

    public LoginPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        driver.findElement(btnLogin).click();
        return new LoginPage(driver);
    }


}
