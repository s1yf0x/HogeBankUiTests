package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private final static Logger logger = LoggerFactory.getLogger(Configuration.class);

    Properties properties = new Properties();

    public boolean getHeadlessExecution() {
        return Boolean.parseBoolean(System.getProperty("headless"));
    }

    public Configuration() {
        try (InputStream inputStream = Configuration.class.getResourceAsStream("/config.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("Unable to read config.properties", e);
        }
    }

    public String getDriver() {
        return properties.getProperty("driver");
    }

    public String getUrl() {
        return properties.getProperty("URL");
    }

    public int getWaitTimeout() {
        return Integer.parseInt(properties.getProperty("wait_timeout"));
    }

    public String getErrorBlankUsername() {
        return properties.getProperty("errorBlankUsername");
    }

    public String getErrorWhiteSpaceUsername() {
        return properties.getProperty("errorWhiteSpaceUsername");
    }

    public String getErrorLess8CharsPassword() {
        return properties.getProperty("errorLess8CharsPassword");
    }

    public String getErrorMore32CharsPassword() {
        return properties.getProperty("errorMore32CharsPassword");
    }

    public String getErrorMustContainDigitsPassword() {
        return properties.getProperty("errorMustContainDigits");
    }

    public String getErrorMustContainUpperCasePassword() {
        return properties.getProperty("errorMustContainUpperCase");
    }

    public double getWithdrawCommission() {
        return Double.parseDouble(properties.getProperty("withdrawCommission"));
    }
    public double getDepositCommission() {
        return Double.parseDouble(properties.getProperty("depositCommission"));
    }

    public String getErrorWithdraw() {
        return properties.getProperty("ErrorWithdraw");
    }

    public String getErrorDeposit() {
        return properties.getProperty("ErrorDeposit");
    }

}
