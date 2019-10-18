package kng.pageObjects;

import kng.driver.Obj_WebElements;
import kng.helpers.Utils;

public class ThankYouPage extends Obj_WebElements {

    public ThankYouPage() {
    }
//-------------------------------------------------------------------------------

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Заказ принят");
    }

    public boolean w_URLContain(int time) {
        return super.wait_urlMatches(time, "confirmation");
    }
//-------------------------------------------------------------------------------

    public int getOrderPrice() {
        String orderAmount = super.element("//div[@class='price']//span")
                .getText();
        return Utils.toInt(orderAmount);
    }

    public String getPageContent() {
        return super.element("//div[@class='block one']")
                .getText();
    }

    public String getAccountsLinkFromTransitionButton() {
        return super.element("//div[@class='flex-item']//a")
                .getAttribute("href");
    }

    public String getOrderDateDelivery() {
        return super.element("//div[@class='flex-container delivery-info bordered']//*[@class='nowrap']")
                .getText();
    }

}
