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
     * This part could be improved, but it required actions by developers. I would to ask developers add some additional
     * attributes to needed elements for more stable tests
     */
    private final By fldUsername = By.xpath("//input[1]");
    private final By fldPassword = By.xpath("//input[@type='password']");
    private final By btnSignup = By.xpath("//button[normalize-space()='SIGNUP']");
    private final By btnLogin = By.xpath("//button[normalize-space()='LOGIN']");


    //This method not used now, but can be used in future
    public LoginPage typeUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldUsername)).sendKeys(username);
        return new LoginPage(driver);
    }

    //This method not used now, but can be used in future
    public LoginPage typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldPassword)).sendKeys(password);
        return new LoginPage(driver);
    }

    public SignupPage clickSignupBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSignup)).click();
        return new SignupPage(driver);
    }

    //This method not used now, but can be used in future
    public LoginPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
        return new LoginPage(driver);
    }


}
