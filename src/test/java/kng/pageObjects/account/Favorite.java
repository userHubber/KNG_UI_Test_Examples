package kng.pageObjects.account;

import kng.driver.Obj_WebElements;
import org.openqa.selenium.WebElement;
import static kng.EnvironmentDomain.DOMAIN;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class Favorite extends Obj_WebElements {

    public Favorite() {
    }
//---------------------------------------------------------------------------

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Избранное");
    }

    public static String getUrl() {
        return DOMAIN + "/account/favorites";
    }
//---------------------------------------------------------------------------

    public void selectAvailableSize(String av_Size) {
        String sizes_drop = "//div[@class]//label[@data-elem='select-sizes']";
        super.element(sizes_drop).click();

        String availableSize
                = "//label[@data-elem='select-sizes']//optgroup[@label='В наличии']//option[@value='" + av_Size + "']";
        super.element(availableSize).click();
    }

    public String getTextMyFavoritesPage() {
        return super.element("//*[@class='accounts-blocks']")
                .getText();
    }

    public int getCountProductsInFavoritePage() {
        return super.getList("//*[@id='favorites-list']//div[@class='inner']")
                .size();
    }

    public void clickUnsubscribeToAllIcons() {
        //count products of acc_favorite page
        int productCount = this.getCountProductsInFavoritePage();

        for (int position = 1; position <= productCount; position++) {
            String productFromPosition = "//*[@id='favorites-list']//div[@data-position='" + position + "']";
            WebElement product = super.element(productFromPosition);
            ACTIONS.moveToElement(product).build().perform();

            String favoriteIconFromPosition
                    = "//*[@id='favorites-list']//div[@data-position='" + position + "']//button[@data-js='product-subscribe']";
            super.element(favoriteIconFromPosition).click();
            super.wait_containAttribute(3, "data-subscribed", "false", favoriteIconFromPosition);
        }
    }

    public void pressAddProductToBasket() {
        super.element("//button[@data-elem='add-to-basket']")
                .click();
    }

    public void pressRegistration() {
        super.element("//div[@class='buttons-login']//*[@data-js='registration']")
                .click();
    }

    public void pressAuthorization() {
        super.element("//div[@class='buttons-login']//*[@data-js='login']")
                .click();
    }
}
