package kng.pageObjects;

import kng.driver.Obj_WebElements;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class PaymentSber extends Obj_WebElements {

    public PaymentSber() {
    }

//----------------------------------------------------------------------
    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Сбербанк");
    }
//----------------------------------------------------------------------

    public boolean w_Discription_textToBe(int time, String text) {
        return super.wait_textToBe(time, text, "//*[@id='description']");
    }

    public String getOrderNumber() {
        String order_number = "//*[@id='orderNumber']";
        String orderNumber;

        for (int i = 0; i < 16; i++) {
            orderNumber = super.element(order_number)
                    .getText();

            if (!orderNumber.contains("-")) {
                return orderNumber;
            } else {
                ROBOT.delay(500);
            }
        }
        return null;
    }

    public int getOrderAmount() {
        String order_amount = "//*[@id='amount']";
        String amount;

        for (int i = 0; i < 16; i++) {
            amount = super.element(order_amount)
                    .getText();

            if (!amount.contains("-")) {
                int $orderAmount
                        = Integer.parseInt(amount.substring(0, amount.length() - 7)
                                .replaceAll("\\s", ""));
                return $orderAmount;
            } else {
                ROBOT.delay(500);
            }
        }
        return 0;
    }

    public boolean w_CardValidationInputs_vis(int time) {
        return super.wait_visible(time, "//*[@id='year-validation']");
    }

//    
    private String card_number() {
        return "//*[@id='iPAN_sub']";
    }

    public void sendCardNumber(String cardNumber) {
        super.element(this.card_number())
                .sendKeys(cardNumber);
    }

    public boolean w_CardNumberInput_vis(int time) {
        return super.wait_visible(time, this.card_number());
    }
//

    public void sendMonth(String validationMonth) {
        super.element("//input[@name='creditCardMonth']")
                .sendKeys(validationMonth);
    }

    public void sendYear(String validationYear) {
        super.element("//input[@name='creditCardYear']")
                .sendKeys(validationYear);
    }

//
    private String name_input() {
        return "//*[@id='iTEXT']";
    }

    public void sendName(String name) {
        super.element(this.name_input())
                .sendKeys(name);
    }

    public boolean w_NameInput_vis(int time) {
        return super.wait_visible(time, this.name_input());
    }
//

//
    private String cvc_input() {
        return "//*[@id='iCVC']";
    }

    public void sendCVC(String cardCvc) {
        super.element(this.cvc_input())
                .sendKeys(cardCvc);
    }

    public boolean w_CVCInput_vis(int time) {
        return super.wait_visible(time, this.cvc_input());
    }
//

    public void pressToPay() {
        super.element("//*[@id='buttonPayment']")
                .click();
    }

}
