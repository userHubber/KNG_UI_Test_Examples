package kng.pageObjects;

import kng.driver.Obj_WebElements;
import kng.helpers.Utils;

public class BasketPopUp extends Obj_WebElements {

    public BasketPopUp() {
    }
//-------------------------------------------------------------------------------------------

    public boolean w_BasketPopup_vis(int time) {
        return super.wait_visible(time, "//*[@id='cartModal']//div[@class='modal-content']");
    }

    public String getBasketUrlFromButton() {
        return super.element("//a[@data-id='basketURL']")
                .getAttribute("href");
    }

    public int getDiscountPrice() {
        String price = "//span[text()='сейчас дешевле']/preceding-sibling::*";
        String $price = super.element(price).getText();
        return Utils.toInt($price);
    }

}
