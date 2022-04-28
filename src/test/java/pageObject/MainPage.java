package pageObject;

import jdk.internal.net.http.common.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, config.getWaitTimeout());

    /**
     * This part could be improved, but it required actions by developers. I would to ask developers add some additional
     * attributes to needed elements for more stable tests
     */
    private final By btnDeposit = By.xpath("//a[@href][1]");
    private final By btnWithdraw = By.xpath("//a[@href][2]");
    private final By lblUsername = By.cssSelector("th[colspan='2']");
    private final By lblBalance = By.xpath("//div[1]/table[1]/tbody[1]/tr[1]/td[2]");
    private final By btnLogout = By.xpath("//button[normalize-space()='Logout']");

    public String getLblUsername() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblUsername)).getText();
    }

    public WithdrawPage clickToWithdraw() {
        wait.until(ExpectedConditions.elementToBeClickable(btnWithdraw)).click();
        return new WithdrawPage(driver);
    }

    public DepositPage clickToDeposit() {
        wait.until(ExpectedConditions.elementToBeClickable(btnDeposit)).click();
        return new DepositPage(driver);
    }

    public MainPage waitForRenewalBalance(double startBalance) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(lblBalance, String.valueOf(startBalance))));
        return new MainPage(driver);
    }

    public double getCurrentBalance() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(lblBalance)).getText());
    }

    public LoginPage clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogout)).click();
        return new LoginPage(driver);
    }
}
