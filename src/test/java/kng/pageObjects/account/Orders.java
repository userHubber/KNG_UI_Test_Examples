package kng.pageObjects.account;

import kng.driver.Obj_WebElements;
import java.util.ArrayList;
import java.util.List;
import kng.helpers.Utils;
import static kng.EnvironmentDomain.DOMAIN;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class Orders extends Obj_WebElements {

    public Orders() {
    }
//---------------------------------------------------------------------------

    public static String getUrl() {
        return DOMAIN + "/account/orders";
    }

    public static String getOrderUrlID(String orderID) {
        return DOMAIN + "/account/orders?id=" + orderID;
    }

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Заказы");
    }
//---------------------------------------------------------------------------

    public int getOrderPrice(String orderNumber, boolean firstOrder) {
        String orderPrise;

        if (firstOrder) {
            String orderPriceInTable
                    = "//div[@data-order-id=" + orderNumber + "]//div[@class='value bold nowrap']";
            orderPrise = super.element(orderPriceInTable).getText();
        } else {
            String orderPriceInTable
                    = "//div[@data-order-id=" + orderNumber + "]//div[@class='nowrap']/span";
            orderPrise = super.element(orderPriceInTable).getText();
        }
        return Utils.toInt(orderPrise);
    }

    public String getOrderDateDelivery() {
        return super.element("//div[@data-elem='delivery-date-text']")
                .getText();
    }

    public int getOrdersPriceSummaFromTable() {
        ArrayList<Integer> $ordersPrices = new ArrayList<>();
        String all_order_prices_in_table
                = "//div[@class='tbody']//div[@class='nowrap']//span[@data-currency='руб.']";
        List<String> ordersPrices = super.getList(all_order_prices_in_table);

        ordersPrices.forEach((ordersPrice) -> {
            $ordersPrices.add(Utils.toInt(ordersPrice));
        });
        return $ordersPrices.stream().mapToInt(Integer::intValue).sum();
    }

    public String getTextMyOrdersPage() {
        return super.element("//*[@class='accounts-blocks']")
                .getText();
    }

    public String getOrderContent(String orderNumber) {
        //блок с заказом
        String order_cart_content
                = "//div[@id='" + orderNumber + "']";
        return super.element(order_cart_content).getText();
    }
//

    private String pay_order_button(String orderNumber) {
        return "//div[@class='status-block']//a[@href='/sberbank/export/" + orderNumber + "']";
    }

    public void pressToPay(String orderNumber) {
        super.element(this.pay_order_button(orderNumber)).click();
    }

    public boolean w_OrderPaymentButton_vis(int time, String orderNumber) {
        return super.wait_visible(time, this.pay_order_button(orderNumber));
    }
//

    public void pressUserInfoClarify() {
        String clarify_button = "//div[@class='status-block']//a[@href='/account/passport']";
        super.wait_clickable(5, clarify_button);
        super.element(clarify_button).click();
    }

    public void pressOrderReturn() {
        super.element("//div[@class='status-block']//a[@href]")
                .click();
    }

    public void pressShowMapPVZ() {
        super.element("//button[@data-js='show-map']")
                .click();
    }

    public boolean w_MapPVZ_vis(int time) {
        return super.wait_visible(time, "//*[@id='pvzMap']");
    }

}
