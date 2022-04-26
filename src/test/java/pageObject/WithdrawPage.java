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
    private final By btnDeposit = By.xpath("//button[normalize-space()='Withdraw']");
    private final By fldDeposit = By.xpath("//div[@class='center']//input");
    private final By lblCommission = By.xpath("//span[4]");

    public double getCommission() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblCommission));
        return Double.parseDouble(driver.findElement(lblCommission).getText());
    }

    public WithdrawPage typeWithdraw(double withdraw) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fldDeposit)).sendKeys(String.valueOf(withdraw));
        return new WithdrawPage(driver);
    }

    private double calculateCommission(double withdraw) {
        return withdraw*(config.getWithdrawCommission()/100);
    }

    public boolean checkCommission(double withdraw) {
        return getCommission()==calculateCommission(withdraw);
    }

    public MainPage clickWithdraw() {
        wait.until(ExpectedConditions.elementToBeClickable(btnDeposit)).click();
        return new MainPage(driver);
    }

    public double calculateWithdrawAndCommission(double withdraw) {
        return getCommission()+withdraw;
    }

}
