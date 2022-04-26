package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(driver, config.getWaitTimeout());

    private final By title = By.xpath("//h1[@class='center']");
    private final By btnDeposit = By.xpath("//a[contains(text(),'?????')]");
    private final By btnWithdraw = By.xpath("//a[contains(text(),'????')]");
    private final By lblUsername = By.cssSelector("th[colspan='2']");
    private final By btnLogout = By.xpath("//button[normalize-space()='Logout']");

    public String getLblUsername() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblUsername));
        return driver.findElement(lblUsername).getText();
    }
}
