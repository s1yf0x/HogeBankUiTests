package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends PageObject {

    WebDriverWait wait = new WebDriverWait(driver, config.getWaitTimeout());

    LoginPage loginPage = new LoginPage(driver);

    /**
     * This part could be improved, but it required actions by developers. I would to ask developers add some additional
     * attributes to needed elements for more stable tests
     */
    private final By fldUsername = By.xpath("//input[1]");
    private final By fldPassword = By.xpath("//input[@type='password']");
    private final By btnSignup = By.xpath("//button[normalize-space()='SIGNUP']");
    private final By lblError = By.cssSelector("span[color='red']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public SignupPage typeUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldUsername)).sendKeys(username);
        return new SignupPage(driver);
    }

    public SignupPage typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldPassword)).sendKeys(password);
        return new SignupPage(driver);
    }

    public MainPage clickSignupBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSignup)).click();
        return new MainPage(driver);
    }

    public String getLblErrorText() {
        return wait.until(ExpectedConditions.elementToBeClickable(lblError)).getText();
    }

    public MainPage signupThenLogin(String username, String password) {
        return loginPage.clickSignupBtn()
                .typeUsername(username)
                .typePassword(password)
                .clickSignupBtn();
    }
}
