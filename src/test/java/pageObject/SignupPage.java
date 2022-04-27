package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends PageObject {

    WebDriverWait wait = new WebDriverWait(driver, config.getWaitTimeout());

    LoginPage loginPage = new LoginPage(driver);

    /**
     * All locators not is best and application implemented so terrible. I would to ask developers add some additional
     * attributes to needed elements for more stable tests
     */
    private final By title = By.xpath("//h1[@class='center']");
    private final By fldUsername = By.xpath("//input[1]");
    private final By fldPassword = By.xpath("//input[@type='password']");
    private final By btnSignup = By.xpath("//button[normalize-space()='SIGNUP']");
    private final By lblError = By.cssSelector("span[color='red']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public SignupPage typeUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldUsername));
        driver.findElement(fldUsername).sendKeys(username);
        return new SignupPage(driver);
    }

    public SignupPage typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldPassword));
        driver.findElement(fldPassword).sendKeys(password);
        return new SignupPage(driver);
    }

    public MainPage clickSignupBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSignup));
        driver.findElement(btnSignup).click();
        return new MainPage(driver);
    }

    public String getLblErrorText() {
        wait.until(ExpectedConditions.elementToBeClickable(lblError));
        return driver.findElement(lblError).getText();
    }

    public MainPage signupThenLogin(String username, String password) {
        return loginPage.clickSignupBtn()
                .typeUsername(username)
                .typePassword(password)
                .clickSignupBtn();
    }
}
