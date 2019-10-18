package kng.pageObjects.account;

import org.openqa.selenium.WebElement;
import kng.driver.Obj_WebElements;
import java.util.ArrayList;
import static kng.EnvironmentDomain.DOMAIN;
/** 
 * <b>context:</b>
 * <br>press - button
 * <br>send -  input
 * <br>check - checkBox
*/
public class Coupons extends Obj_WebElements {

    public Coupons() {
    }
//---------------------------------------------------------------------------

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Купоны");
    }

    public static String getUrl() {
        return DOMAIN + "/account/coupons";
    }
//---------------------------------------------------------------------------

    public String getTextMyCouponPage() {
        return super.element("//*[@class='accounts-blocks']")
                .getText();
    }

    public void sendCode(String promoCode) {
        super.sendKeysToClear(promoCode, "//*[@id='promo-code']//input");
    }
//

    private String active_coupons() {
        return "//div[@class='coupons']//div[@class='coupon']";
    }

    private String coupons_area() {
        return "//div[@class='coupons']";
    }

    public boolean pressActivateCode(boolean activate) {
        int count1;
        String active_coupons = this.active_coupons();
        WebElement coupons_area = super.element(this.coupons_area());
        WebElement promocode_activate_button = super.element("//*[@id=\"discount-block\"]//button");
        int areaHeight = coupons_area.getSize().height;
        
        if (areaHeight == 0) {
            count1 = 0;
        } else {
            count1 = super.getList(active_coupons).size();
        }

        promocode_activate_button.click();

        if (activate) {
            for (int i = 0; i < 10; i++) {
                int count2 = super.getList(active_coupons).size();
                if (count2 == count1) {
                    ROBOT.delay(200);
                } else if (count2 > count1) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<String> getActiveCouponsList() {

        String coupons_area = this.coupons_area();
        String active_coupons = this.active_coupons();
        int areaHeight = super.element(coupons_area).getSize().height;
        
        if (areaHeight > 0) {
            return super.getList(active_coupons);
        }
        return null;
    }
}
