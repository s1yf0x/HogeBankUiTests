package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.SignupPage;
import utilities.Generator;
import utilities.User;

public class SignUpTests extends BaseTest {

    WebDriver driver;
    Generator generator = new Generator();
    LoginPage loginPage;
    SignupPage signupPage;
    MainPage mainPage;

    User userValid = new User(generator.generateUsername(false), generator.generatePassword(10, true, true, true));;
    User userBlankUsername = new User("", generator.generatePassword(10, true, true, true));
    User userWhiteSpaceUsername = new User(generator.generateUsername(true), generator.generatePassword(10, true, true, true));;
    User userLess8CharsPassword = new User(generator.generateUsername(false), generator.generatePassword(5, true, true, true));
    User userMore32CharsPassword = new User(generator.generateUsername(false), generator.generatePassword(35, true, true, true));
    User userWithoutDigitsPassword = new User(generator.generateUsername(false), generator.generatePassword(10, true, true, false));
    User userWithoutUppercasePassword = new User(generator.generateUsername(false), generator.generatePassword(10, false, true, true));

    @BeforeMethod(alwaysRun = true, description = "Get driver and url")
    protected void setup() {
        driver = getDriver();
        driver.get(config.getUrl());

        generator = new Generator();
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterClass(alwaysRun = true, description = "Cleaning up")
    protected void clean() {
        driverManager.quitDriver();
    }

    @DataProvider(name = "getCredentials")
    public Object[][] getCredentials() {
        return new Object[][]{{userBlankUsername.getUsername(), userBlankUsername.getPassword(), config.getErrorBlankUsername()},
                {userWhiteSpaceUsername.getUsername(), userWhiteSpaceUsername.getPassword(), config.getErrorWhiteSpaceUsername()},
                {userLess8CharsPassword.getUsername(), userLess8CharsPassword.getPassword(), config.getErrorLess8CharsPassword()},
                {userMore32CharsPassword.getUsername(), userMore32CharsPassword.getPassword(), config.getErrorMore32CharsPassword()},
                {userWithoutDigitsPassword.getUsername(), userWithoutDigitsPassword.getPassword(), config.getErrorMustContainDigitsPassword()},
                {userWithoutUppercasePassword.getUsername(), userWithoutUppercasePassword.getPassword(), config.getErrorMustContainUpperCasePassword()}};
    }

    @Test(groups = "SignUp", description = "Positive test for signup process")
    public void positiveSignUpTest() {
        loginPage.clickSignupBtn()
                .typeUsername(userValid.getUsername())
                .typePassword(userValid.getPassword())
                .clickSignupBtn();

        Assert.assertEquals(mainPage.getLblUsername(), userValid.getUsername(), "User not logged after signup or show invalid username of Main page");
    }

    @Test(groups = "SignUp", dataProvider = "getCredentials",  description = "Test for signup process")
    public void negativeSignUpTests(String username, String password, String expectedResult) {
        loginPage.clickSignupBtn()
                .typeUsername(username)
                .typePassword(password)
                .clickSignupBtn();

        Assert.assertEquals(signupPage.getLblErrorText(), expectedResult, "Didn't get error about invalid username or password");
    }
}
