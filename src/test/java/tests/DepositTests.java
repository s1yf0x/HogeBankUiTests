package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import utilities.Generator;
import utilities.User;

public class DepositTests extends BaseTest {

    WebDriver driver;
    Generator generator = new Generator();
    LoginPage loginPage;
    SignupPage signupPage;
    MainPage mainPage;
    WithdrawPage withdrawPage;
    DepositPage depositPage;
    User userValid = new User(generator.generateUsername(false), generator.generatePassword(10, true, true, true));

    @BeforeMethod(alwaysRun = true, description = "Get driver and url")
    protected void setup() {
        driver = getDriver();
        driver.get(config.getUrl());

        generator = new Generator();
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        mainPage = new MainPage(driver);
        withdrawPage = new WithdrawPage(driver);
        depositPage = new DepositPage(driver);
    }

    @AfterClass(alwaysRun = true, description = "Cleaning up")
    protected void clean() {
        driverManager.quitDriver();
    }

    @DataProvider(name = "getWithdraw")
    public Object[][] getWithdraw() {
        return new Object[][]{{"500"},
                {"1"},
                {"33"},
                {"100"},
                {"10.5"}};
    }

    @DataProvider(name = "getInvalidWithdraw")
    public Object[][] getInvalidWithdraw() {
        return new Object[][]{{"-500"},
                {"text"}};
    }

    @Test(groups = "Deposit", dataProvider = "getWithdraw",  description = "Test for calculating withdraw")
    public void checkCalculationCommission(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());
        Assert.assertTrue(mainPage.clickToDeposit().typeDeposit(withdraw).checkCommission(Double.parseDouble(withdraw)), "Commission calculated wrong");
    }

    @Test(groups = "Deposit", dataProvider = "getWithdraw",  description = "Test for balance after withdraw")
    public void checkAccountBalance(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());
        double startBalance = mainPage.getCurrentBalance();
        double finalDeposit = mainPage.clickToDeposit().typeDeposit(withdraw).calculateFinalDeposit(Double.parseDouble(withdraw));
        double endBalance = depositPage.clickDeposit().waitForRenewalBalance(startBalance).getCurrentBalance();
        Assert.assertEquals(endBalance, startBalance+finalDeposit, "Balance after deposit isn't correct");
    }

    @Test(groups = "Deposit", dataProvider = "getWithdraw",  description = "Test for balance after withdraw")
    public void checkCalculationFinalWithdraw(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());
        double finaCalculatedDeposit = mainPage.clickToDeposit().typeDeposit(withdraw).calculateFinalDeposit(Double.parseDouble(withdraw));
        double finalDeposit = depositPage.getFinalDeposit();
        Assert.assertEquals(finalDeposit, finaCalculatedDeposit, "Final deposit calculated wrong");
    }

    @Test(groups = "Deposit", dataProvider = "getInvalidWithdraw",  description = "Test for getting error with invalid withdraw")
    public void invalidWithdraw(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());
        mainPage.clickToDeposit().typeDeposit(withdraw).clickDeposit();
        Assert.assertEquals(depositPage.getLblErrorText(), config.getErrorWithdraw(), "Didn't get error about invalid username or password");
    }
}
