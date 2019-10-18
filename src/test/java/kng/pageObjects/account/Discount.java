package kng.pageObjects.account;

import kng.driver.Obj_WebElements;
import static kng.EnvironmentDomain.DOMAIN;

public class Discount extends Obj_WebElements {

    public Discount() {
    }
//--------------------------------------------------------------   

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Персональная скидка");
    }

    public static String getUrl() {
        return DOMAIN + "/account/personal-discount";
    }
//--------------------------------------------------------------

    public String getTextMyDiscountPage() {
        return super.element("//*[@class='accounts-blocks']")
                .getText();
    }

}
