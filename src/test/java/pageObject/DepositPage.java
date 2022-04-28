package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DepositPage extends PageObject {

    /**
     *     Decide not to create parent class for DepositPage.class and for WithdrawPage.class despite the similarity
     *     'cause they can change and become completely different
     */

    public DepositPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, config.getWaitTimeout());

    /**
     * This part could be improved, but it required actions by developers. I would to ask developers add some additional
     * attributes to needed elements for more stable tests
     */
    private final By btnDeposit = By.xpath("//button[normalize-space()='Deposit']"); //example for improvement <button data-test-id="deposit">Deposit</button>
    private final By fldDeposit = By.xpath("//div[@class='center']//input");
    private final By lblCommission = By.xpath("//span[4]");
    private final By lblFinalDeposit = By.xpath("//span[6]");
    private final By lblErrorFailed = By.xpath("//span[@color='red']");

    public double getCommission() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(lblCommission)).getText());
    }

    public DepositPage typeDeposit(String withdraw) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldDeposit)).sendKeys(withdraw);
        return new DepositPage(driver);
    }

    private double calculateCommission(double withdraw) {
        return withdraw*(config.getDepositCommission()/100);
    }

    public boolean checkCommission(double withdraw) {
        return getCommission()==calculateCommission(withdraw);
    }

    public MainPage clickDeposit() {
        wait.until(ExpectedConditions.elementToBeClickable(btnDeposit)).click();
        return new MainPage(driver);
    }

    public double calculateFinalDeposit(double withdraw) {
        return withdraw-getCommission();
    }

    public double getFinalDeposit() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(lblFinalDeposit)).getText());
    }

    public String getLblErrorText() {
        return wait.until(ExpectedConditions.elementToBeClickable(lblErrorFailed)).getText();
    }
}
