package kng.pageObjects;

import kng.driver.Obj_WebElements;

public class QuickModal extends Obj_WebElements {

    public QuickModal() {
    }
//---------------------------------------------------------------------------

    public boolean pressFavorite(int time, boolean subscribed) {
        String icon = "//*[@class='quick']//button[@data-js='product-subscribe']";
        super.element(icon).click();
        if (subscribed) {
            return super.wait_containAttribute(time, "data-subscribed", "true", icon);
        }
        return super.wait_containAttribute(time, "data-subscribed", "false", icon);
    }

}
