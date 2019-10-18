package kng.pageObjects;

import org.openqa.selenium.WebElement;
import kng.driver.Obj_WebElements;
import org.openqa.selenium.WebDriverException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import kng.helpers.Utils;
import static kng.EnvironmentDomain.DOMAIN;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class Catalog extends Obj_WebElements {

    public Catalog() {
    }

//----------------------------------------------------------------------------------
    public static String getBasicCatalogUrl() {
        return DOMAIN + "/search?q=";
    }

    public boolean w_urlSortPa(int time) {
        return super.wait_urlContains(time, "sort=pa");
    }

    public boolean w_urlSortPd(int time) {
        return super.wait_urlContains(time, "sort=pd");
    }

    public boolean w_urlSortDd(int time) {
        return super.wait_urlContains(time, "sort=dd");
    }

    public boolean w_urlSecondPage(int time) {
        return super.wait_urlContains(time, "page=2");
    }

    public boolean w_url120PerPage(int time) {
        return super.wait_urlContains(time, "quantity_per_page=120");
    }
//----------------------------------------------------------------------------------

//
    private String h1() {
        return "//header//h1";
    }

    public boolean w_H1TextContain(int time, String text) {
        return super.wait_textMatches(time, text, h1());
    }

    public boolean w_BreadCrumbsTextContain(int time, String text) {
        String bread_crumbs = "//div[@class='breadcrumb category brands']";
        return super.wait_textMatches(time, text, bread_crumbs);
    }

    public boolean getBrandBannersAreaContain() {
        return super.find("//*[@id='catalog-row']");
    }

    public boolean w_TagPanel_vis(int time) {
        String tag_panel = "//*[@id='filter-tags']";
        return super.wait_visible(time, tag_panel);
    }

    public boolean clickMenuLinkFemale(int time) {
        super.element("//*[@id='catalog-categories']//li[@class='active']/a[@data-mp='Женщинам']")
                .click();
        return super.wait_textMatches(time, "Женские товары", h1());
    }

    public boolean clickMenuLinkFemaleClother(int time) {
        super.element("//*[@id='catalog-categories']//li/a/span[text()='Одежда']")
                .click();
        return super.wait_textMatches(time, "Женская одежда", h1());
    }

    public boolean clickMenuLinkFemaleShoes(int time) {
        super.element("//*[@id='catalog-categories']//li/a/span[text()='Обувь']")
                .click();
        return super.wait_textMatches(time, "Женская обувь", h1());
    }
//

    public boolean pressQuickModal(int productLocation) {
        String button_quick_modal
                = "//*[@id='catalog-list']/section//div[@data-position='" + productLocation + "']//button[@data-js='quick']";
        String quick_modal = "//*[@id='quick-view']//div[@class='container']//div[@class='quick']";

        this.moveToProductPerPosition(productLocation);
        super.element(button_quick_modal).click();
        return super.wait_visible(5, quick_modal);
    }

    public ArrayList<String> getLeftMenuCategoriesList() {
        String left_menu_categories = "//*[@id='catalog-categories']//li[@class='active']/a";
        return super.getList(left_menu_categories);
    }

    public ArrayList<String> getFiltersNamesList() {
        String filters_panel = "//*[@id='filters-menu']/div[@class='btn-place']";
        return super.getList(filters_panel);
    }

    public ArrayList<String> getSortersList() {
        String sorter_list = "//*[@id='filters-sort']//span";
        return super.getList(sorter_list);
    }

    public ArrayList<Integer> getListingPrices() {
        String prices = "//div[@class='price']";
        ArrayList<String> product_prices = super.getList(prices);
        ArrayList<Integer> returned = new ArrayList<>();

        for (String price : product_prices) {
            returned.add(Utils.toInt(price));
        }
        return returned;
    }

    public Map<Integer, ArrayList<String>> getListingSizes() {
        Map<Integer, ArrayList<String>> sizes = new HashMap<>();
        int countProductInCatalog = super.getList("//div[@class='product-item']").size();//products count in catalog
        for (int productPosition = 1; productPosition <= countProductInCatalog; productPosition++) {
            String productSizes
                    = "//div[@data-position=" + "'" + String.valueOf(productPosition) + "']//div[@class='sizes']//button";
            ArrayList<String> $sizes = super.getList("data-text", productSizes);
            sizes.put(productPosition, $sizes);
        }
        return sizes;
    }

    public ArrayList<Integer> getListingDiscountPrices() {
        String price_discount = "//div[@class='labels']/span[@data-discount]";
        ArrayList<String> prices = getList("data-discount", price_discount);
        ArrayList<Integer> returned = new ArrayList<>();
        for (String st : prices) {
            returned.add(Utils.toInt(st));
        }
        return returned;
    }

    public ArrayList<String> getListingBrands() {
        String brands = "//*[@class='brand']";
        return super.getList(brands);
    }

//
    private String filter_apply_button(String filterName) {
        return "//*[@id='filters-menu']//div[@data-facet='" + filterName + "']//button[@type='submit']";
    }
//######

    private boolean clickFilterFacet(int time, String filterName) {
        String filter_facet
                = "//*[@id='filters-menu']//div[@data-facet='" + filterName + "']";
        String applyButton = this.filter_apply_button(filterName);

        for (int i = 0; i < 10; i++) {
            try {
                super.element(filter_facet).click();
                return super.wait_visible(time, applyButton);
            } catch (WebDriverException ignore) {
                ROBOT.delay(500);
            }
        }
        return false;
    }
//######

    public boolean clickFilterPriceFacet(int time) {
        return this.clickFilterFacet(time, "price");
    }

    public boolean clickFilterSizeFacet(int time) {
        return this.clickFilterFacet(time, "size");
    }

    public boolean clickFilterBrandFacet(int time) {
        return this.clickFilterFacet(time, "brand");
    }

    public boolean clickFilterSeasonFacet(int time) {
        return this.clickFilterFacet(time, "season");
    }

    public boolean clickFilterMaterialFacet(int time) {
        return this.clickFilterFacet(time, "material");
    }

    public boolean clickFilterColorFacet(int time) {
        return this.clickFilterFacet(time, "color");
    }

    public boolean clickFilterCountryFacet(int time) {
        return this.clickFilterFacet(time, "country");
    }

    public boolean clickFilterSpecialFacet(int time) {
        return this.clickFilterFacet(time, "special");
    }

//##############################################
    private void pressFilterApply(String filterName) {
        String applyButton = this.filter_apply_button(filterName);
        super.wait_clickable(3, applyButton);
        super.element(applyButton).click();

    }
//############################################## 

    public void pressFilterPriceApply() {
        this.pressFilterApply("price");
    }

    public void pressFilterBrandApply() {
        this.pressFilterApply("brand");
    }

    public void pressFilterSeasonApply() {
        this.pressFilterApply("season");
    }

    public void pressFilterMaterialApply() {
        this.pressFilterApply("material");
    }

    public void pressFilterColorApply() {
        this.pressFilterApply("color");
    }

    public void pressFilterCountryApply() {
        this.pressFilterApply("country");
    }

    public void pressFilterSpecialApply() {
        this.pressFilterApply("special");
    }

    public void pressFilterSizeApply() {

        for (int i = 0; i < 11; i++) {
            try {
                this.pressFilterApply("size");
                break;
            } catch (WebDriverException ignore) {
                super.ROBOT.delay(100);
            }
        }
    }
//    

    public void sendAndCheckSizeInFasetList(String size) {
        String faset_input
                = "//*[@id='filters-menu']//div[@data-facet='size']//div[@class='search-input']/input";
        String size_in_drop_list
                = "//*[@id='filters-menu']//div[@data-facet='size']//div[@class='dropdown-menu']//input[@value='" + size + "']/..";

        super.sendKeysToClear(size, faset_input);
        super.wait_visible(3, size_in_drop_list);
        super.element(size_in_drop_list).click();
    }

    public void sendMaxLimitPriceToFaset(String filterLimit) {
        super.sendKeysToClear(filterLimit, "//input[@data-js='price-filter-right']");
    }

    public String checkFilterPriceUnder1000() {
        String checkbox_under1000
                = "//*[@id='filters-menu']/div[@data-facet='price']//span[text()='до 1 000']";
        super.element(checkbox_under1000).click();
        return "1 000";
    }

//
//#################
    private String checkFirstInFacetDropList(String filterName) {
        String $first_checkbox_in_drop = "//*[@id='filters-menu']//div[@data-facet='" + filterName + "']//span[@data-num]";
        super.wait_clickable(5, $first_checkbox_in_drop);
        WebElement first_checkbox_in_drop
                = super.element($first_checkbox_in_drop);
        first_checkbox_in_drop.click();
        return first_checkbox_in_drop.getText();
    }
//#################    

    public String checkFirstBrandInFacetList() {
        return this.checkFirstInFacetDropList("brand");
    }

    public String checkFirstSeasonInFacetList() {
        return this.checkFirstInFacetDropList("season");
    }

    public String checkFirstColorInFacetList() {
        return this.checkFirstInFacetDropList("color");
    }

    public String checkFirstMaterialInFacetList() {
        return this.checkFirstInFacetDropList("material");
    }

    public String checkFirstCountryInFacetList() {
        return this.checkFirstInFacetDropList("country");
    }

    public String checkFirstSpecialInFacetList() {
        return this.checkFirstInFacetDropList("special");
    }
//

    public void clickSortersFacet() {
        for (int i = 0; i < 10; i++) {
            try {
                super.element("//*[@id='myfilters-sort']").click();
                break;
            } catch (WebDriverException ignore) {
                ROBOT.delay(1000);
            }
        }
    }

//
//##################################    
    private void clickSorter(String type) {
        String sorter = "//*[@id='filters-sort']//span[text()='" + type + "']";
        super.wait_clickable(3, sorter);
        super.element(sorter).click();
    }
//################################## 

    public void clickSorterPaInDropList() {
        this.clickSorter("Цена по возрастанию");
    }

    public void clickSorterPdInDropList() {
        this.clickSorter("Цена по убыванию");
    }

    public void clickSorterDdInDropList() {
        this.clickSorter("По размеру скидки");
    }
//

    public void pressCatalogSecondPage() {
        String button = "//*[@id='catalog-list']//div[@class='pages']//a[text()='2']";
        super.element(button).click();
    }

    public void pressShowMoreProductsInCatalog() {
        super.element("//*[@id='catalog-list']//div[@class='show-more']//a")
                .click();
    }

    public void pressShow120ProductsInCatalog() {
        super.element("//*[@id='catalog-list']//div/button[@data-num='120']")
                .click();
    }

//
    private String brand_subscribe_button() {
        return "//button[@data-js='brand-subscribe']";
    }

    public String getBrandNameFromSubscribeButton() {
        return super.element(this.brand_subscribe_button())
                .getAttribute("data-brand");
    }

    public void pressBrandSubscribe() {
        super.element(this.brand_subscribe_button())
                .click();
    }

    public boolean w_BrandSubscribedConfirm(int time) {
        return super.wait_containAttribute(time, "data-subscribed", "true", this.brand_subscribe_button());
    }
//

//    
//###########
    private void moveToProductPerSKU(String productSKU) {
        String product_per_position = "//*[@id='catalog-list']/section//div[@data-id='" + productSKU + "']";
        WebElement productPerPosition = super.element(product_per_position);
        super.ACTIONS.moveToElement(productPerPosition).build().perform();
    }
//###########

    public int getSizesCountPerProduct(String productSKU, boolean toHover) {
        if (toHover) {
            this.moveToProductPerSKU(productSKU);
        }
        String product_per_position = "//*[@id='catalog-list']/section//div[@data-id='" + productSKU + "']//div[@class='price']/span";
        return super.getList(product_per_position).size();
    }
//

//    
//###########
    private void moveToProductPerPosition(int productLocation) {
        String product_per_position = "//*[@id='catalog-list']/section//div[@data-position='" + productLocation + "']";
        WebElement productPerPosition = super.element(product_per_position);
        super.ACTIONS.moveToElement(productPerPosition).build().perform();
    }
//###########

    public void moveCoursorToSixtiethProduct() {
        this.moveToProductPerPosition(60);
    }

    public boolean pressFavoritePerPosition(int productLocation) {
        this.moveToProductPerPosition(productLocation);
        String favorite_icon_per_position
                = "//*[@id='catalog-list']/section//div[@data-position='" + productLocation + "']//button[@data-js='product-subscribe']";
        super.element(favorite_icon_per_position).click();
        return super.wait_containAttribute(productLocation, "data-subscribed", "true", favorite_icon_per_position);
    }
//

}
