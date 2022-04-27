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
    LoginPage loginPage;
    SignupPage signupPage;
    MainPage mainPage;
    WithdrawPage withdrawPage;

    Generator generator = new Generator();
    User userValid = new User(generator.generateUsername(false), generator.generatePassword(10, true, true, true));

    @BeforeMethod(alwaysRun = true, description = "Get driver and url")
    protected void setup() {
        driver = getDriver();
        driver.get(config.getUrl());

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

    @DataProvider(name = "getInvalidWithdraw")
    public Object[][] getInvalidWithdraw() {
        return new Object[][]{{"-500"},
                {"text"}};
    }

    @Test(groups = "Withdraw", dataProvider = "getWithdraw",  description = "Test for calculating withdraw")
    public void checkCalculationCommission(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());

        Assert.assertTrue(mainPage.clickToWithdraw().typeWithdraw(withdraw).checkCommission(Double.parseDouble(withdraw)), "Commission calculated wrong");
    }

    @Test(groups = "Withdraw", dataProvider = "getWithdraw",  description = "Test for balance after withdraw")
    public void checkAccountBalance(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());

        double startBalance = mainPage.getCurrentBalance();
        double sumOfWithdrawAndCommission = mainPage.clickToWithdraw().typeWithdraw(withdraw).calculateFinalWithdraw(Double.parseDouble(withdraw));
        double endBalance = withdrawPage.clickWithdraw().waitForRenewalBalance(startBalance).getCurrentBalance();

        Assert.assertEquals(endBalance, startBalance-sumOfWithdrawAndCommission, "Balance after withdraw is correct");
    }

    @Test(groups = "Withdraw", dataProvider = "getWithdraw",  description = "Test for balance after withdraw")
    public void checkCalculationFinalWithdraw(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());

        double sumOfWithdrawAndCommission = mainPage.clickToWithdraw().typeWithdraw(withdraw).calculateFinalWithdraw(Double.parseDouble(withdraw));
        double finalWithdraw = withdrawPage.getFinalWithdraw();

        Assert.assertEquals(finalWithdraw, sumOfWithdrawAndCommission, "Final withdraw calculated wrong");
    }

    @Test(groups = "Withdraw", dataProvider = "getInvalidWithdraw",  description = "Test for getting error with invalid withdraw")
    public void invalidWithdraw(String withdraw) {
        signupPage.signupThenLogin(userValid.getUsername(), userValid.getPassword());

        mainPage.clickToWithdraw().typeWithdraw(withdraw).clickWithdraw();

        Assert.assertEquals(withdrawPage.getLblErrorText(), config.getErrorWithdraw(), "Didn't get error about invalid username or password");

    }
}
