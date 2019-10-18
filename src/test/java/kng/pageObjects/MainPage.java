package kng.pageObjects;

import org.openqa.selenium.WebElement;
import kng.driver.Obj_WebElements;
import java.util.ArrayList;
import static kng.EnvironmentDomain.DOMAIN;

public class MainPage extends Obj_WebElements {

    public MainPage() {
    }
//----------------------------------------------------------------------------------------------

    public boolean w_Title(int timeSec) {
        return super.wait_titleContains(timeSec,
                "KUPIVIP - Интернет магазин брендовой одежды и обуви с доставкой по Москве и России");
    }

    public boolean waitUrl(int time) {
        return super.wait_urlToBe(time, DOMAIN + '/');
    }
//----------------------------------------------------------------------------------------------

    public String getTextFooter() {
        return super.element("//*[@data-elem='footer']")
                .getText();
    }

//
    private String icon_basket() {
        return "//*[@id='quick-menu']//a[@data-elem='basket']";
    }

    public int getBasketCountByHeader() {
        String count = super.element(this.icon_basket())
                .getAttribute("data-num");
        return Integer.parseInt(count);
    }
//    

    private String icon_favorite() {
        return "//*[@id='quick-menu']//a[@data-elem='favorites']";
    }

    public boolean w_FavoritePerHeaderCountingContain(int time, int countFav) {
        String count = String.valueOf(countFav);
        return super.wait_containAttribute(time, "data-num", count, this.icon_favorite());
    }

    public int getFavoriteCountByHeader() {
        String count = super.element(this.icon_favorite())
                .getAttribute("data-num");
        return Integer.parseInt(count);
    }
//

    private String account_icon() {
        return "//*[@data-elem='personal-item']";
    }

//######################    
    private void move_to_account_icon() {
        WebElement icon = super.element(this.account_icon());
        super.ACTIONS.moveToElement(icon).build().perform();
    }
//######################    

    public void clickLoginLinkPerAccountIconsList() {
        this.move_to_account_icon();
        String login_link_in_drop_list = "//*[@data-js='login']";
        super.element(login_link_in_drop_list).click();
    }

    public void clickAccountIcon() {
        super.element(this.account_icon())
                .click();
    }

    public ArrayList<String> getHeaderAccountDropList() {
        this.move_to_account_icon();
        String account_list_of_links = "//li[@data-elem='personal-item']//ul[@class='popout']//li";
        return super.getList(account_list_of_links);
    }

}
