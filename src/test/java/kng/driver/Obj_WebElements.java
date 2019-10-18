package kng.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.Robot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriverException;

public class Obj_WebElements extends Driver {

    protected final Actions ACTIONS = new Actions(driver);
    protected final Robot ROBOT = RobotClicker.ROBOT;
//------------------------------------------------------------------------

    public Obj_WebElements() {
    }
//------------------------------------------------------------------------

    private WebDriverWait driverWait(int sec) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, sec);
        return webDriverWait;
    }
//------------------------------------------------------------------------

    protected void alertAccept() {
        driver.switchTo().alert().accept();
    }
//    

    protected WebElement element(String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        WebElement webelement = driver.findElement(by$Selector);
        return webelement;
    }

    protected boolean find(String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        try {
            driver.findElement(by$Selector);
            return true;
        } catch (WebDriverException ignore) {
        }
        return false;
    }
//

    protected boolean wait_urlContains(int sec, String fraction) {
        try {
            return driverWait(sec).until(ExpectedConditions.urlContains(fraction));
        } catch (TimeoutException e) {
        }
        return false;
    }

    protected boolean wait_urlToBe(int sec, String fraction) {
        try {
            return driverWait(sec).until(ExpectedConditions.urlToBe(fraction));
        } catch (TimeoutException e) {
        }
        return false;
    }

    protected boolean wait_urlMatches(int sec, String fraction) {
        try {
            return driverWait(sec).until(ExpectedConditions.urlMatches(fraction));
        } catch (TimeoutException e) {
            System.out.println("No URL matches - " + fraction);
        }
        return false;
    }

    protected boolean wait_titleNotContains(int sec, String text) {
        try {
            driverWait(sec).until(ExpectedConditions.not(ExpectedConditions.titleContains(text)));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Title contains - " + text);
        }
        return false;
    }

    protected boolean wait_titleContains(int sec, String text) {
        try {
            driverWait(sec).until(ExpectedConditions.titleContains(text));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Title not contains - " + text);
        }
        return false;
    }

    protected boolean wait_presence(int sec, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        try {
            driverWait(sec).until(ExpectedConditions.presenceOfElementLocated(by$Selector));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element did not presence - " + selector);
        }
        return false;
    }

    protected boolean wait_visible(int sec, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        try {
            driverWait(sec).until(ExpectedConditions.visibilityOfElementLocated(by$Selector));
            return true;
        } catch (TimeoutException e) {
        }
        return false;
    }

    protected boolean wait_inVisible(int sec, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        try {
            driverWait(sec).until(ExpectedConditions.invisibilityOfElementLocated(by$Selector));
            return true;
        } catch (TimeoutException e) {
        }
        return false;
    }

    protected boolean wait_clickable(int sec, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        try {
            driverWait(sec).until(ExpectedConditions.elementToBeClickable(by$Selector));
            return true;
        } catch (TimeoutException e) {
        }
        return false;
    }

    protected boolean wait_containAttribute(int i, String attribute, String value, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        try {
            driverWait(i).until(ExpectedConditions.attributeContains(by$Selector, attribute, value));
            return true;
        } catch (TimeoutException e) {
            System.out.println("This value of attribute did not present" + selector);
        }
        return false;
    }

    protected boolean wait_textMatches(int sec, String text, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        try {
            driverWait(sec).until(ExpectedConditions.textMatches(by$Selector, Pattern.compile(text)));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Text not matches - " + text);
        }
        return false;
    }

    protected boolean wait_textToBe(int sec, String text, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        try {
            driverWait(sec).until(ExpectedConditions.textToBe(by$Selector, text));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Text not to be - " + text);
        }
        return false;
    }

//
    protected List<WebElement> getElementsList(String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        List<WebElement> elements = driver.findElements(by$Selector);
        return elements;
    }
//

    protected ArrayList<String> getList(String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        ArrayList<String> result = new ArrayList<>();
        List<WebElement> elements = driver.findElements(by$Selector);

        for (WebElement element : elements) {
            String text = element.getText();
            if (!text.isEmpty()) {
                result.add(text);
            }
        }
        return result;
    }

    protected ArrayList<String> getList(String attribute, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        ArrayList<String> result = new ArrayList<>();
        List<WebElement> elements = driver.findElements(by$Selector);

        elements.forEach((element) -> {
            result.add(element.getAttribute(attribute));
        });
        return result;
    }

//
    protected void sendKeysToClear(String charSequence, String selector) {
        String $selector = selector;
        By by$Selector = By.xpath($selector);
        WebElement webelement = driver.findElement(by$Selector);
        webelement.clear();
        webelement.sendKeys(charSequence);
    }

}
