package kng.pageObjects;

import kng.driver.Obj_WebElements;
import java.util.ArrayList;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class OrderSplit extends Obj_WebElements {

    public OrderSplit() {
    }
//----------------------------------------------------------------------------------------------------

    public boolean w_URLContain(int time) {
        return super.wait_urlContains(time, "split");
    }

//----------------------------------------------------------------------------------------------------
//
    private String days_intervals() {
        return "//select[@data-elem='interval-date']/option";
    }

    public ArrayList<String> getDaysIntervalsList() {
        String days_intervals = this.days_intervals();
        ArrayList<String> days_list = super.getList(days_intervals);
        days_list.remove(0);//выберите дату (не нужен для сравнения)
        days_list.remove(1);//глючит при выборе времени следующего дня, все время пишет 09-12 (первый)
        return days_list;
    }

    public void selectDayPerDateList(String intervDay) {
        String $intervDay
                = this.days_intervals() + "[contains (text(), '" + intervDay + "')]";
        String daysDrop = this.days_intervals();
        WebElement day = super.element(daysDrop);
        super.ACTIONS.clickAndHold(day).perform();
        super.element($intervDay).click();
        super.wait_visible(3, $intervDay);
    }
//

//
    private String times_intervals(String intervalDay) {
        return "//*[@data-elem='select-block']//select[@data-interval-date='" + intervalDay + "']/option";
    }

    public ArrayList<String> getTimesIntervalsList(String intervalDay) {
        ArrayList<String> times_list = super.getList(this.times_intervals(intervalDay));
        times_list.remove(0);//выберите время (не нужен для сравнения)
        return times_list;
    }

    public void selectTimePerIntervalsList(String intervTime, String intervalDay) {
        String $intervTime
                = this.times_intervals(intervalDay) + "[contains (text(), '" + intervTime + "')]";
        String timesDrop = this.times_intervals(intervalDay);
        WebElement time = super.element(timesDrop);
        super.ACTIONS.clickAndHold(time).perform();
        super.element($intervTime).click();
        super.wait_visible(3, $intervTime);
    }
//

    public boolean pressOrderSplitting() {
        try {
            super.element("//*[@id='confirm-split']").click();
            return true;
        } catch (WebDriverException e) {
        }
        return false;
    }

    public boolean w_orderWarehouseDate_toBe(int time, String warehoseDate) {
        String warehouseDate = "//div[@class='button-block']//span[@data-elem='select-date']";
        return super.wait_textToBe(time, warehoseDate, warehouseDate);
    }

}
