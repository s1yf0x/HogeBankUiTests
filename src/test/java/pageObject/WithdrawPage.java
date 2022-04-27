package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WithdrawPage extends PageObject {

    public WithdrawPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, config.getWaitTimeout());

    private final By title = By.xpath("//h1[@class='center']");
    private final By btnWithdraw = By.xpath("//button[normalize-space()='Withdraw']");
    private final By fldWithdraw = By.xpath("//div[@class='center']//input");
    private final By lblCommission = By.xpath("//span[4]");
    private final By lblFinalWithdraw = By.xpath("//span[6]");
    private final By lblErrorFailed = By.xpath("//span[@color='red']");

    public double getCommission() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblCommission));
        return Double.parseDouble(driver.findElement(lblCommission).getText());
    }

    public WithdrawPage typeWithdraw(String withdraw) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldWithdraw)).sendKeys(withdraw);
        return new WithdrawPage(driver);
    }

    private double calculateCommission(double withdraw) {
        return withdraw*(config.getWithdrawCommission()/100);
    }

    public boolean checkCommission(double withdraw) {
        return getCommission()==calculateCommission(withdraw);
    }

    public MainPage clickWithdraw() {
        wait.until(ExpectedConditions.elementToBeClickable(btnWithdraw)).click();
        return new MainPage(driver);
    }

    public double calculateFinalWithdraw(double withdraw) {
        return getCommission()+withdraw;
    }

    public double getFinalWithdraw() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(lblFinalWithdraw)).getText());
    }

    public String getLblErrorText() {
        wait.until(ExpectedConditions.elementToBeClickable(lblErrorFailed));
        return driver.findElement(lblErrorFailed).getText();
    }

}
