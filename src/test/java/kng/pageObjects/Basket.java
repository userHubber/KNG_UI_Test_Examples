package kng.pageObjects;

import java.util.ArrayList;
import kng.driver.Obj_WebElements;
import kng.helpers.Utils;
import org.openqa.selenium.WebElement;

public class Basket extends Obj_WebElements {

    public Basket() {
    }
//---------------------------------------------------------------------------

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Корзина товаров");
    }

    public boolean w_URLContain(int time) {
        return super.wait_urlContains(time, "basket");
    }
//---------------------------------------------------------------------------

    public int getOrdersCountByH1() {
        String count
                = "//*[@id='basket-count']";
        String orderCount = super.element(count).getText();
        return Utils.toInt(orderCount);
    }

    public ArrayList<String> getEuroWarningTextList() {
        return super.getList("//*[@class='warning']/div[1]");
    }

    public int getTimerValue() {
        String timer = "//*[@class='round-timer']//*[@time]";
        super.wait_textMatches(8, "[^0-9]", timer);
        String time = super.element(timer).getText();
        return Integer.parseInt(time.substring(0, 2));
    }

    public String getEuroMessage() {
        return super.element("//div[@data-elem='euroMessage']").getText();
    }

//
    private String order_group(String productSKU) {
        String orderGroup = "//*[@data-sku='" + productSKU + "']/ancestor::*[@data-group]";
        return orderGroup;
    }

    public String getDeliveryDateInfo(String productSKU) {
        String $message = this.order_group(productSKU)
                + "//*[@class='delivery-info']/*[@data-elem='deliveryEstimateMessage']";

        String $date = this.order_group(productSKU)
                + "//*[@class='delivery-info']/*[@data-elem='deliveryEstimateTotal']";

        String date = super.element($date).getText();
        String message = super.element($message).getText();

        String info = message + ' ' + date;
        return info;
    }

    public int getTotalAmount(String productSKU) {
        String total
                = this.order_group(productSKU)
                + "//*[@data-elem='totalAmountWithoutDelivery']";
        String $total = super.element(total).getText().replaceAll("\\s", "");
        return Integer.parseInt($total);
    }

    public String getH2TextByOrderHeader(String productSKU) {
        String h2Group = this.order_group(productSKU) + "//header/h2";
        return super.element(h2Group).getText();
    }

    public String getAdditionalTextByOrderHeader(String productSKU) {
        String h2Group = this.order_group(productSKU) + "//header/div";
        return super.element(h2Group).getText();
    }

    public boolean pressOrderCreate(String productSKU) {
        String button
                = this.order_group(productSKU)
                + "//*[@class='place-order']//*[text()='Оформить заказ']";
        WebElement $button = super.element(button);
        String linkToCheckoutByButton = $button.getAttribute("href");
        $button.click();
        return super.wait_urlContains(5, linkToCheckoutByButton);
    }

    private String product_amount(String productSKU) {
        return this.order_group(productSKU) + "//*[@data-elem='price']";
    }

    public int getProductAmount(String productSKU) {
        String amount = super.element(this.product_amount(productSKU)).getText();
        int productAmount = Utils.toInt(amount);
        return productAmount;
    }

//################################################################################  
    private boolean pressChangeProductCount(String productSKU, String changeType) {
        WebElement productAmount = super.element(this.product_amount(productSKU));

        String $countProduct
                = this.order_group(productSKU) + "//*[@class='number-control']//input[@value]";

        String button
                = this.order_group(productSKU)
                + "//*[@class='number-control']//button[@data-js='" + changeType + "']";

        String productAmountBefore = productAmount.getText();

        int countProduct = Integer.parseInt(super.element($countProduct)
                .getAttribute("value"));

        super.element(button).click();

        if (changeType.equals("increment")) {
            super.wait_containAttribute(5, "value", String.valueOf(countProduct + 1), $countProduct);
        }

        if (changeType.equals("decrement")) {
            super.wait_containAttribute(5, "value", String.valueOf(countProduct - 1), $countProduct);
        }

        for (int i = 0; i < 11; i++) {
            String productAmountAfter = productAmount.getText();

            if (!productAmountAfter.equals(productAmountBefore)) {
                return true;
            } else {
                ROBOT.delay(300);
            }
        }
        return false;
    }
//################################################################################    

    public boolean pressIncrement(String productSKU) {
        return pressChangeProductCount(productSKU, "increment");
    }

    public boolean pressDecrement(String productSKU) {
        return pressChangeProductCount(productSKU, "decrement");
    }
//

    public boolean pressDelete(String productSKU) {

        String button = this.order_group(productSKU) + "//button[@data-js='basket-remove']";
        String countH2 = "//*[@id='baskets']//header/h2";
        int contentSizeBefore = super.getList(countH2).size();

        String basketOrdersCount = "//*[@id='basket-count']";
        String countOrdersInBasketBefore = super.element(basketOrdersCount).getText().replaceAll("[^0-9]", "");

        int $countOrdersInBasketBefore = Integer.parseInt(countOrdersInBasketBefore);

        super.element(button).click();

        for (int i = 0; i < 11; i++) {
            int contentSizeAfter = super.getList(countH2).size();
            if (contentSizeAfter == contentSizeBefore - 1) {
                break;
            }
            ROBOT.delay(300);
        }

        String countOrdersInBasketAfter = super.element(basketOrdersCount).getText().replaceAll("[^0-9]", "");
        int $countOrdersInBasketAfter;

        if (!countOrdersInBasketAfter.equals("")) {
            $countOrdersInBasketAfter = Integer.parseInt(countOrdersInBasketAfter);
        } else {
            $countOrdersInBasketAfter = 0;
        }
        return $countOrdersInBasketAfter == $countOrdersInBasketBefore - 1;
    }

}
