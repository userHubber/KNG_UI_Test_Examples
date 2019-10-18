package kng.pageObjects.infoPages;

import static kng.EnvironmentDomain.DOMAIN;
import kng.driver.Obj_WebElements;

public class Order extends Obj_WebElements {

    public Order() {
    }

    public static String getUrl() {
        return DOMAIN + "/i/order";
    }

    public boolean w_Title(int timeSec) {
        return super.wait_titleContains(timeSec,
                "Заказ");
    }
//----------------------------------------------------------------------------------------------

    public String getTextContent() {
        return super.element("//*[@class='i-order']")
                .getText();
    }
}
