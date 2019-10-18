package kng.pageObjects;

import kng.driver.Obj_WebElements;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import kng.helpers.Utils;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class Checkout extends Obj_WebElements {

    public Checkout() {
    }
//---------------------------------------------------------------------------

    public boolean w_TitleNotCheckout(int time) {
        return super.wait_titleNotContains(time, "Оформление заказа");
    }
//----------------------------------------------------------------------------

    private String order_create_button() {
        return "//button[@data-js='process']";
    }

    public boolean w_OrderCreateButton_clickable(int time) {
        return super.wait_clickable(time, this.order_create_button());
    }

    public boolean w_DisabledOrderCreateButton(int time) {
        return super.wait_containAttribute(time, "class", "disabled", this.order_create_button());
    }

//############
    private boolean checkObject(String checkObject, int delayPeriodPause) {
        String check_object = checkObject;
        for (int i = 0; i < 9; i++) {
            try {
                super.element(check_object).click();
                return true;
            } catch (WebDriverException ignore) {
                ROBOT.delay(delayPeriodPause);
            }
        }
        return false;
    }
//############

    public boolean pressOrderCreate() {
        String order_create_button = this.order_create_button();
        return this.checkObject(order_create_button, 1000);
    }
//

    public boolean checkPrivateFlat() {
        String flat_private
                = "//*[@id='deliveries']//label[@for='private-house']";
        return this.checkObject(flat_private, 500);
    }

    public boolean checkCardPayment() {
        String card_checkbox
                = "*//img[@alt='оплата картой']";
        return this.checkObject(card_checkbox, 500);
    }

    public boolean checkPVZDelivery() {
        String pickup_checkbox
                = "//*[@id='deliveries']//label[contains (text(),'Самовывоз')]";
        return this.checkObject(pickup_checkbox, 500);
    }

    public boolean pressToOpenAllPVZMap() {
        String all_pvz_button
                = "//*[@id='deliveries']//label[contains (text(),'Все пункты')]";
        return this.checkObject(all_pvz_button, 500);
    }

    public boolean pressCouponApply() {
        String button = "//button[@data-js='promo-input']";
        return this.checkObject(button, 500);
    }

//#######################
    private boolean sendToInputForm(String userInfo, String inputType) {

        for (int i = 0; i < 10; i++) {
            try {
                super.sendKeysToClear(userInfo, inputType);
                ROBOT.delay(500);
                ROBOT.keyPress(KeyEvent.VK_ENTER);
                ROBOT.keyRelease(KeyEvent.VK_ENTER);
                ROBOT.delay(500);
                return true;
            } catch (WebDriverException ignore) {
                ROBOT.delay(1000);
            }
        }
        return false;
    }
//#######################   

    public boolean sendName(String userName) {
        return this.sendToInputForm(userName, "//*[@id='username']");
    }

    public boolean sendMail(String userMail) {
        return this.sendToInputForm(userMail, "//*[@id='emailField']");
    }

    public boolean sendPhone(String userPhone) {
        return this.sendToInputForm(userPhone, "//*[@id='phoneField']");
    }

    public boolean sendAdress(String userAdress) {
        return this.sendToInputForm(userAdress, "//*[@data-elem='address']");
    }

    public boolean sendCity(String userCity) {
        return this.sendToInputForm(userCity, "//*[@id='cityName']");
    }

    public boolean sendComment(String userComment) {
        return this.sendToInputForm(userComment, "//*[@id='additionalAddressInfo']");
    }

    public boolean sendFlat(String userFlat) {
        return this.sendToInputForm(userFlat, "//*[@id='address-flat']");
    }

    public boolean sendCoupon(String coupon) {
        return this.sendToInputForm(coupon, "//input[@name='code']");
    }

    public boolean sendCityWithAdressBY(String cityAndAdress) {
        return this.sendToInputForm(cityAndAdress, "//*[@id='address_by']");
    }
//

    public boolean sendCityKZ(String userCity) {
        super.ACTIONS.moveToElement(super.element("//*[@id='kzCity']")).click().perform();
        String almaty = "//option[text()='" + userCity + "']";
        super.element(almaty).click();
        return this.wait_visible(3, almaty);
    }

    public boolean w_LinkWithCompatibleEuroCatalog_vis(int time) {
        return super.wait_visible(time, "//a[@data-elem='eurolink']");
    }

    public boolean w_NoCouponMessage_vis(int time) {
        String message = "//section[@class='container']//"
                + "div[normalize-space (text())='На товары по этой акции купоны и промокоды неприменимы']";
        return super.wait_visible(time, message);
    }

    public boolean w_NotAvailablePersonalDiscountMessageToProduct_vis(int time) {
        String message = "//span[text()='Персональная скидка не применима к этому товару']";
        return super.wait_visible(time, message);
    }

    public boolean w_NotAvailablePersonalDiscountMessageToOrder_vis(int time) {
        String message = "//div[text()='Персональная скидка не применима ни к одному из товаров']";
        return super.wait_visible(time, message);
    }

    public boolean w_AdressField_inVis(int time) {
        return super.wait_inVisible(time, "//*[@id='address_ru']");
    }

    public boolean w_Choky500EuroMessage_vis(int time) {
        return super.wait_visible(time, "//p[@data-id='euroMessage']");
    }

//
    private String minimal_amount_euro_message() {
        return "//div[@class='min-basket-euro-counter']//span[@class='red']";
    }

    public boolean w_MinimalAmountEuroMessage_vis(int time) {
        return super.wait_visible(time, this.minimal_amount_euro_message());
    }

    public String getTextMinimalAmountEuroMessage() {
        return super.element(this.minimal_amount_euro_message())
                .getText();
    }
//

    public ArrayList<String> getErrorsFromRequiredFields() {
        return super.getList("//div[@class='error']");
    }

    public boolean w_PVZAdressInDeliveryArea_vis(int time) {
        return super.wait_visible(time, "//div[@class='checked-view']/p[1]");
    }

    public boolean w_PVZStorageConditionInDeliveryArea_vis(int time) {
        return super.wait_visible(time, "//div[@class='checked-view']/p[2]");
    }

//
    private String days_intervals() {
        return "//option[text()='Выберите дату']/ancestor::select[@class]/option";
    }

    public ArrayList<String> getDaysIntervalsList() {
        ArrayList<String> days_list = super.getList(this.days_intervals());
        days_list.remove(0);//выберите дату (не нужен для сравнения)
        days_list.remove(days_list.size() - 1);//перезвоните мне
        return days_list;
    }

    public void selectDayPerDateList(String intervDay) {
        String $intervDay
                = "//div[@name='intervals']//option[contains (text(), '" + intervDay + "')]";
        String daysDrop = this.days_intervals();
        WebElement day = super.element(daysDrop);
        super.ACTIONS.clickAndHold(day).perform();
        super.element($intervDay).click();
    }
//

//
    private String times_intervals(String intervalDay) {
        return "//select[@data-interval-date='" + intervalDay + "']/option";
    }

    public ArrayList<String> getTimesIntervalsList(String intervalDay) {
        ArrayList<String> times_list = super.getList(this.times_intervals(intervalDay));
        times_list.remove(0);//выберите время (не нужен для сравнения)
        return times_list;
    }

    public void selectTimePerIntervalsList(String intervTime, String intervalDay) {
        String $intervTime
                = "//select[@data-interval-date='" + intervalDay + "']//option[contains (text(), '" + intervTime + "')]";
        String timesDrop = this.times_intervals(intervalDay);
        WebElement time = super.element(timesDrop);
        super.ACTIONS.clickAndHold(time).perform();
        super.element($intervTime).click();
    }
//

    public String getDeliveryDate() {
        String date = super.element("//span[@data-field='deliveryEstimateTotal']")
                .getText();
        return date;
    }

//
    private String product_count_field() {
        return "//span[@class='number-control']//input";
    }

    public int getProductCount() {
        String count = super.element(this.product_count_field())
                .getAttribute("value");
        return Integer.parseInt(count);
    }

    public boolean pressProductIncrement() {
        int count = this.getProductCount();
        String countIncrements = String.valueOf(count + 1);
        String countInput = this.product_count_field();
        String increment_button = "//button[@data-js='increment']";
        for (int i = 0; i < 9; i++) {
            try {
                super.element(increment_button).click();
                ROBOT.delay(2000);
                break;
            } catch (WebDriverException ignore) {
                ROBOT.delay(500);
            }
        }
        return super.wait_containAttribute(5, "value", countIncrements, countInput);
    }

    public boolean pressProductDecrement() {
        int count = this.getProductCount();
        String countDecrements = String.valueOf(count - 1);
        String countInput = this.product_count_field();
        String decrement_button = "//button[@data-js='decrement']";
        for (int i = 0; i < 9; i++) {
            try {
                super.element(decrement_button).click();
                ROBOT.delay(2000);
                break;
            } catch (WebDriverException ignore) {
                ROBOT.delay(500);
            }
        }
        return super.wait_containAttribute(5, "value", countDecrements, countInput);
    }
//

    public void pressDeleteProduct() {
        super.element("//button[@data-js='basket-remove']")
                .click();
    }

    public void selectCouponPerDropList(String couponAnnotationContain) {
        String couponsDrop = "//select[@name='couponId']";
        super.element(couponsDrop).click();

        String couponPerList
                = "//select[@name='couponId']//option[contains(text(),'" + couponAnnotationContain + "')]";
        super.element(couponPerList).click();
    }

    public void checkCoupon() {
        super.element("//li[@data-elem='discount-no']//span")
                .click();
    }

    public boolean w_CouponFieldError_vis(int time) {
        String error = "//*[@data-elem='discounts']//div[@class='error']";
        return super.wait_visible(time, error);
    }

//
    private String personal_discount() {
        return "//div[@data-field='accumulative-discount']";
    }

    public int getPersonalDiscountAmount() {
        String discountValue = super.element(this.personal_discount())
                .getText();
        return Utils.toInt(discountValue);
    }

    public boolean w_PersonalDiscountAmount_inVis(int time) {
        return super.wait_inVisible(time, this.personal_discount());
    }
    //   

    public int getPersonalDiscountPercent() {
        String discountPercent
                = super.element("//*[@data-elem='discounts']//div[contains(text(),'Персональная')]")
                        .getText();
        return Utils.toInt(discountPercent);
    }

    private String coupon_discount() {
        return "//div[@data-field='coupon-discount']";
    }

    public boolean w_CouponDiscountAmount_vis(int time) {
        return super.wait_visible(time, this.coupon_discount());
    }

    public boolean w_CouponDiscountAmount_inVis(int time) {
        return super.wait_inVisible(time, this.coupon_discount());
    }

    public int getCouponDiscountAmount() {
        String discount = super.element(this.coupon_discount())
                .getText();
        return Utils.toInt(discount);
    }

    public boolean selectCouponsCancelFromDropList() {
        this.selectCouponPerDropList("Выберите бонус-купон");
        return super.wait_inVisible(5, this.coupon_discount());
    }

    public int getCardDiscountPercent() {
        String discount = super.element("//*[@class='cards-block']//div[@class='checkout-info']")
                .getText();
        return Utils.toInt(discount);
    }

    public int getCardDiscountValue() {
        String discount = "//*[@data-id='typedDiscount']//div[@data-field='typedDiscount']";
        super.wait_visible(5, discount);
        String value = super.element(discount).getText();
        return Utils.toInt(value);
    }

    public int getDeliveryAmount() {
        String deliveryValue = super.element("//div[@data-field='deliveryPrice']")
                .getText();
        return Utils.toInt(deliveryValue);
    }

    public int getProductsAmount() {
        String products_amount = super.element("//div[@data-field='itemsAmount']")
                .getText();
        return Utils.toInt(products_amount);
    }

    public int getProductAmount(String productSKU) {
        String products_amount = super.element("//*[@data-sku='" + productSKU + "']//*[@data-field='price']")
                .getText();
        return Utils.toInt(products_amount);
    }

//
    private String order_amont() {
        return "//div[@data-field='totalAmount']";
    }

    public String getTextOrderAmount() {
        return super.element(this.order_amont()).getText();
    }

    public int getOrderAmount() {
        String order_amount = super.element(this.order_amont())
                .getText();
        return Utils.toInt(order_amount);
    }

}
