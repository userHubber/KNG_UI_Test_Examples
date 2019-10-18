package kng.pageObjects.account;

import java.util.ArrayList;
import kng.driver.Obj_WebElements;

public class LeftMenu extends Obj_WebElements {

    public LeftMenu() {
    }
//--------------------------------------------------------------

    public ArrayList<String> getLeftMenuItems() {
        return super.getList("//*[@class='left-menu']//li//span");
    }

//#########################################
    private void clickMenuLink(String link) {
        super.element("//*[@class='left-menu']//span[text()='" + link + "']")
                .click();
    }
//#########################################

    public void clickFavorites() {
        this.clickMenuLink("Избранное");
    }

    public void clickOrders() {
        this.clickMenuLink("Заказы");
    }

    public void clickMybrands() {
        this.clickMenuLink("Мои бренды");
    }

    public void clickMycoupon() {
        this.clickMenuLink("Купоны");
    }

    public void clickPersonalDiscount() {
        this.clickMenuLink("Персональная скидка");
    }

    public void clickLogin() {
        this.clickMenuLink("Вход");
    }

    public void clickLogOut() {
        this.clickMenuLink("Выход");
    }

}
