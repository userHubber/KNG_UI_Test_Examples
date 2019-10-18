package kng.pageObjects.account;

import kng.driver.Obj_WebElements;
import java.util.ArrayList;
import static kng.EnvironmentDomain.DOMAIN;
/** 
 * <b>context:</b>
 * <br>press - button
 * <br>send -  input
 * <br>check - checkBox
*/
public class Brands extends Obj_WebElements {

    public Brands() {
    }
//---------------------------------------------------------------------------

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Мои бренды");
    }

    public static String getUrl() {
        return DOMAIN + "/account/brands";
    }
//---------------------------------------------------------------------------

    public String getTextMyBandsPage() {
        return super.element("//*[@class='accounts-blocks']")
                .getText();
    }

    public void pressSubscribeInModal(String brandName) {
        super.element("//div[@class='item']//button[@data-brand='" + brandName + "']")
                .click();
    }
//

    private String unsubscribe_buttons() {
        return "//div[@class='title']//button[@data-js='brand-subscribe']";
    }

    public void pressUnsubscribeToAllBrands() {

        String unsubscribe_buttons = this.unsubscribe_buttons();
        ArrayList<String> subscr_brands = super.getList("data-brand", unsubscribe_buttons);

        for (int i = subscr_brands.size() - 1; i >= 0; i--) {
            String button = "//div[@class='title']//button[@data-brand='" + subscr_brands.get(i) + "']";
            super.element(button).click();
            super.wait_containAttribute(i, "data-subscribed", "false", button);
        }
    }

    public ArrayList<String> getAllSubscribedBrandsNames() {
        return super.getList("data-brand", this.unsubscribe_buttons());
    }
//

    public boolean w_NonSubscribeInH1_vis(int time) {
        return super.wait_visible(time, "//h1[text()='Нет избранных брендов']");
    }

    public void clickInputForOpenModalWithBrandList() {
        super.element("//div[@data-elem='open-modal']")
                .click();
    }
//

    private String input_to_search_brand_in_modal() {
        return "//input[@data-elem='search-brand']";
    }

    public boolean w_ModalWithBrandsList_vis(int time) {
        return super.wait_visible(time, this.input_to_search_brand_in_modal());
    }

    public void sendBrandInModal(String brandFirstWord) {
        super.sendKeysToClear(brandFirstWord, this.input_to_search_brand_in_modal());
    }
//

    public boolean w_SubscribeConfirmInModal(int time) {
        String subscribe_button_in_modal = "//div[@class='item']//button[@data-js='brand-subscribe']";
        return super.wait_containAttribute(time, "data-subscribed", "true", subscribe_button_in_modal);
    }

}
