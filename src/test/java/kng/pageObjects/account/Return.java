package kng.pageObjects.account;

import org.openqa.selenium.WebElement;
import kng.driver.Obj_WebElements;
import java.awt.event.KeyEvent;
import static kng.EnvironmentDomain.DOMAIN;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class Return extends Obj_WebElements {

    public Return() {
    }
//---------------------------------------------------------------------------

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Оформление возврата");
    }

    public static String getUrl(String orderNumber) {
        return DOMAIN + "/account/orders/return/" + orderNumber;
    }
//---------------------------------------------------------------------------  

    public void clickSelectAllProductsReturnCheckbox() {
        super.element("//*[@id='return-items']//label[@for]")
                .click();
    }

//    
//##########################
    private void selectReturningCauseFromDropList(String causeNumber) {
        WebElement return_causes_drop = super.element("//*[@id='cancelReasonSelect_0']");
        super.ACTIONS.clickAndHold(return_causes_drop).perform();

        String cause_bigSize = "//*[@id='cancelReasonSelect_0']/option[@value='" + causeNumber + "']";
        super.element(cause_bigSize).click();
    }
//##########################

    public void selectBigSizeCause() {
        this.selectReturningCauseFromDropList("1");
    }

    public void selectCrashProductCause() {
        this.selectReturningCauseFromDropList("4");
    }
//

    public void pressReturnFormFilling() {
        super.element("//button[@name='submitForm']").click();
    }

    public void sendName(String name) {
        super.sendKeysToClear(name, "//*[@id='fullName']");
    }

    public void sendBankAccount(String bankAccount) {
        super.sendKeysToClear(bankAccount, "//*[@id='recipientAccount1']");
    }

    public void sendPrivateBankAccount(String bankAccount) {
        super.sendKeysToClear(bankAccount, "//*[@id='recipientAccount2']");
    }

    public void sendCardNum(String card_num) {
        super.sendKeysToClear(card_num, "//*[@id='cardNumber']");
    }

    public void sendBicBank(String bicBank) {
        super.sendKeysToClear(bicBank, "//*[@id='recipientBank']");
    }

    public void sendCourierCity(String city) {
        super.sendKeysToClear(city, "//*[@id='city']");
        this.keyCombinationForSendingAdressToCourier();
    }

    public void sendCourierAdress(String address) {
        super.sendKeysToClear(address, "//*[@id='address']");
        this.keyCombinationForSendingAdressToCourier();
    }

    public void checkCourierPrivateHouse() {
        String private_flat_flag = "//label[@for='private-house']";
        super.wait_visible(5, private_flat_flag);
        super.element(private_flat_flag).click();
    }
//

    private String courier_call_in_button() {
        return "//label[@for='courier']";
    }

    public void pressCourierCallIn() {
        super.element(this.courier_call_in_button())
                .click();
    }

    public boolean w_CourierCallInButton_inVis(int time) {
        return super.wait_inVisible(time, this.courier_call_in_button());
    }

    public boolean w_CourierCallInButton_vis(int time) {
        return super.wait_visible(time, this.courier_call_in_button());
    }
//   

    private String courier_pick_button() {
        return "//button[@data-elem='courier-call']";
    }

    public void pressCourierPick() {
        super.wait_visible(5, this.courier_pick_button());
        super.element(this.courier_pick_button())
                .click();
    }

    public void selectCourierCityFromDropList() {
        ROBOT.delay(300);
        super.element("//div[@data-elem='address-select']")
                .click();
    }

    public void selectCourierNewAdressFromDropList() {
        String newAdress = "//*[text()='+ Новый адрес']";
        super.wait_visible(5, newAdress);
        super.element(newAdress)
                .click();
    }

    public void pressConfirmCourierAdress() {
        super.element("//*[@data-elem='check-address']")
                .click();
    }

    public boolean w_CourierEmptyAdressWarning_vis(int time) {
        String warning = "//div[text()='Начните вводить адрес и выберите его из списка']";
        return super.wait_visible(time, warning);
    }

    public boolean w_CourierEmptyAdressError_vis(int time) {
        String error = "//div[text()='Сперва необходимо ввести полный адрес']";
        return super.wait_visible(time, error);
    }

    public boolean w_CourierAdressNotFullError_vis(int time) {
        String error = "//div[text()='Укажите адрес полностью, в том числе дом и квартиру']";
        return super.wait_visible(time, error);
    }

    public boolean w_CourierDateAbsentError_vis(int time) {
        String error = "//div[text()='Выберите дату и время']";
        return super.wait_visible(time, error);
    }

//######
    private void keyCombinationForSendingAdressToCourier() {
        ROBOT.delay(100);
        ROBOT.keyPress(KeyEvent.VK_DOWN);
        ROBOT.delay(100);
        ROBOT.keyPress(KeyEvent.VK_ENTER);
        ROBOT.keyRelease(KeyEvent.VK_ENTER);
        ROBOT.delay(100);
    }

}
