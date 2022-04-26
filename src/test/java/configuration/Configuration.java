package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private final static Logger logger = LoggerFactory.getLogger(Configuration.class);

    Properties props = new Properties();

    public boolean getHeadlessExecution() {
        return Boolean.parseBoolean(System.getProperty("headless"));
    }

    public Configuration() {
        try (InputStream inputStream = Configuration.class.getResourceAsStream("/config.properties")) {
            props.load(inputStream);
        } catch (Exception e) {
            logger.error("Unable to read config.properties", e);
        }
    }

    public String getDriver() {
        return props.getProperty("driver");
    }

    public String getUrl() {
        return props.getProperty("URL");
    }

    public int getWaitTimeout() {
        return Integer.parseInt(props.getProperty("wait_timeout"));
    }

    public String getErrorBlankUsername() {
        return props.getProperty("errorBlankUsername");
    }

    public String getErrorWhiteSpaceUsername() {
        return props.getProperty("errorWhiteSpaceUsername");
    }

    public String getErrorLess8CharsPassword() {
        return props.getProperty("errorLess8CharsPassword");
    }

    public String getErrorMore32CharsPassword() {
        return props.getProperty("errorMore32CharsPassword");
    }

    public String getErrorMustContainDigitsPassword() {
        return props.getProperty("errorMustContainDigits");
    }

    public String getErrorMustContainUpperCasePassword() {
        return props.getProperty("errorMustContainUpperCase");
    }

    public double getWithdrawCommission() {
        return Double.parseDouble(props.getProperty("withdrawCommission"));
    }
    public double getDepositCommission() {
        return Double.parseDouble(props.getProperty("depositCommission"));
    }

    public String getErrorBlankWithdraw() {
        return props.getProperty("ErrorBlankWithdraw");
    }

}
