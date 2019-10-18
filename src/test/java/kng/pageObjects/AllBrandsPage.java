package kng.pageObjects;

import org.openqa.selenium.WebElement;
import kng.driver.Obj_WebElements;
import static kng.EnvironmentDomain.DOMAIN;

public class AllBrandsPage extends Obj_WebElements {

    public AllBrandsPage() {
    }
//---------------------------------------------------------------------------

    public static String getUrl() {
        return DOMAIN + "/brands";
    }
//---------------------------------------------------------------------------

    private String brandToSelect(String brand) {
        return "//div[@class='wrap']//a[text()='" + brand + "']";
    }

    public boolean selectFlagToBrandSubscribe(String brand) {
        WebElement brandToSelect = super.element(this.brandToSelect(brand));
        super.ACTIONS.moveToElement(brandToSelect).build().perform();

        String flag = "//button[@data-brand='" + brand + "']";
        super.element(flag).click();

        return wait_containAttribute(5, "data-subscribed", "true", flag);
    }

    public String getSelectedBrandLink(String brand) {
        WebElement brandSelected = super.element(this.brandToSelect(brand));
        return brandSelected.getAttribute("href");
    }

}
