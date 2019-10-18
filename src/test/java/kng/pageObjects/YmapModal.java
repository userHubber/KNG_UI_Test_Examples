package kng.pageObjects;

import org.openqa.selenium.WebElement;
import kng.driver.Obj_WebElements;
import java.util.ArrayList;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class YmapModal extends Obj_WebElements {

    public YmapModal() {
    }
//-------------------------------------------------------------------------------------

    private String ymaps_modal() {
        return "//*[@id='pointsMap']";
    }

    public boolean w_YmapsModal_vis(int time) {
        return super.wait_visible(time, this.ymaps_modal());
    }

    public void clickToCenterYmap() {
        WebElement ymap = super.element(this.ymaps_modal());
        ROBOT.delay(2000);
        int y = ymap.getSize().height / 2;
        int x = ymap.getSize().width / 2;
        super.ACTIONS.moveToElement(ymap, x, y)
                .click().perform();
    }
//

    private String market_bubble_button() {
        return "//button[text()='Выбрать']";
    }

    public void pressMarketBubble() {
        super.element(this.market_bubble_button())
                .click();
    }

    public boolean w_MarketBubbleButton_vis(int time) {
        return super.wait_visible(time, market_bubble_button());
    }
//

    private String adress_searching_input() {
        return "//input[@placeholder='Адрес или объект']";
    }

    public boolean w_AdressSearchingInput_vis(int time) {
        return super.wait_visible(time, this.adress_searching_input());
    }

    public void sendAdressToSearching(String marketAdress) {
        super.element(this.adress_searching_input())
                .sendKeys(marketAdress);
    }
//

    public void pressAdressSearching() {
        ROBOT.delay(500);
        super.element("//ymaps[text()='Найти']")
                .click();
    }

    public ArrayList<String> getPvzFiltersList() {
        return super.getList("//img[@class='img-icon']/parent::ymaps");
    }
}
