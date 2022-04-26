package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.SignupPage;
import pageObject.WithdrawPage;
import utilities.Generator;
import utilities.User;

public class WithdrawTests extends BaseTest {
    WebDriver driver;
    Generator generator = new Generator();
    LoginPage loginPage;
    SignupPage signupPage;
    MainPage mainPage;
    WithdrawPage withdrawPage;
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

    @Test(groups = "Withdraw", dataProvider = "getWithdraw",  description = "Test for signup withdraw")
    public void checkCalculationCommission(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());
        Assert.assertTrue(mainPage.clickToWithdraw().typeWithdraw(Double.parseDouble(withdraw)).checkCommission(Double.parseDouble(withdraw)), "Commission calculated wrong");
    }

    @Test(groups = "Withdraw", dataProvider = "getWithdraw",  description = "Test for signup withdraw")
    public void checkAccountBalance(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());
        double startBalance = mainPage.getCurrentBalance();
        double sumOfWithdrawAndCommission = mainPage.clickToWithdraw().typeWithdraw(Double.parseDouble(withdraw)).calculateWithdrawAndCommission(Double.parseDouble(withdraw));
        double endBalance = withdrawPage.clickWithdraw().waitForRenewalBalance(startBalance).getCurrentBalance();
        Assert.assertEquals(endBalance, startBalance-sumOfWithdrawAndCommission, "Balance after withdraw is correct");
    }
    //TODO: Add negative tests

}
