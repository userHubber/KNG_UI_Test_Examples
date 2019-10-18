package kng.driver;

import org.openqa.selenium.WebDriver;

public class Driver {

    public Driver() {
    }

    protected static WebDriver driver;

    protected void driverAssign() {
        final DriverManager DM = new DriverManager();
        driver = DM.getDriver();
    }
}
