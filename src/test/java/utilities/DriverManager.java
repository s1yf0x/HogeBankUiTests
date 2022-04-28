package utilities;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class DriverManager {

    protected ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    protected abstract WebDriver createDriver();

    public void quitDriver() {
        if (null != drivers.get()) {
            try {
                drivers.get().quit(); // shut down session
                drivers.remove();
            } catch (Exception e) {
                System.err.println("Unable to shut down WebDriver");
                e.printStackTrace(System.err);
            }
        }
    }

    public WebDriver getDriver() {
        if (null == drivers.get()) {
            drivers.set(this.createDriver());
        }
        drivers.get().manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);

        return drivers.get();
    }
}
