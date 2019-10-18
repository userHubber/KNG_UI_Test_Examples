package kng.driver;

import java.util.ArrayList;
import java.util.Set;
import java.util.Date;
import java.awt.Robot;
import org.openqa.selenium.JavascriptExecutor;
import java.awt.event.KeyEvent;
import org.openqa.selenium.Cookie;

public class DriverTools extends Driver {

    private final Robot ROBOT = RobotClicker.ROBOT;

    public DriverTools() {
    }

    protected void driverClose() {
        driver.close();
    }

    protected void driverCloseQuit() {
        driver.close();
        driver.quit();
    }

    public void driverGet(String url) {
        driver.get(url);
    }

    protected void driverClear() {
        driver.manage().deleteAllCookies();
    }

    protected Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    protected String getAuthCookie() {

        try {
            return driver.manage().getCookieNamed("kupivipAuth").toString();
        } catch (NullPointerException e) {
        }
        return null;
    }

    protected String getAuthCookieValue() {

        try {
            return driver.manage().getCookieNamed("kupivipAuth").getValue();
        } catch (NullPointerException e) {
        }
        return null;
    }

    protected void setAuthCookie(String authCookieValue) {
        String domain = driver.manage().getCookieNamed("kupivipAuth").getDomain();
        String path = driver.manage().getCookieNamed("kupivipAuth").getPath();
        Date expiry = driver.manage().getCookieNamed("kupivipAuth").getExpiry();
        driver.manage().deleteCookieNamed("kupivipAuth");
        Cookie cook = new Cookie("kupivipAuth", authCookieValue, domain, path, expiry, true);
        driver.manage().addCookie(cook);
    }

    protected String getUrl() {
        return driver.getCurrentUrl();
    }

    protected void refreshPage() {
        driver.navigate().refresh();
    }

    protected void openNewTab() {
        ROBOT.keyPress(KeyEvent.VK_CONTROL);
        ROBOT.keyPress(KeyEvent.VK_T);
        ROBOT.keyRelease(KeyEvent.VK_CONTROL);
        ROBOT.keyRelease(KeyEvent.VK_T);
        ROBOT.delay(2000);
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
    }

    protected void focusFirstTab() {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(0));
    }

    protected void focusSecondTab() {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
    }

    protected void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    protected void clearLocalStorage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("window.localStorage.clear();"));
    }
}
