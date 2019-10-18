package kng.pageObjects.admin;

import kng.driver.Obj_WebElements;
import static kng.EnvironmentDomain.DOMAIN;
/** 
 * <b>context:</b>
 * <br>press - button
 * <br>send -  input
 * <br>check - checkBox
*/
public class Retail extends Obj_WebElements {

    public Retail() {
    }
//---------------------------------------------------------------------------

    public static String getRegistrationUrl() {
        return DOMAIN + "/protected/admin/retail_registration/reg";
    }

    public static String getConfirmUrl() {
        return DOMAIN + "/protected/admin/retail_registration/confirm?fields=true";
    }
//---------------------------------------------------------------------------

    public void sendMail(String mail) {
        super.sendKeysToClear(mail, "//input[@type='email']");
    }

    public void sendPhone(String phone) {
        super.sendKeysToClear(phone, "//input[@type='tel']");
    }

    public void checkConfirm() {
        super.element("//input[@type='checkbox']")
                .click();
    }

    public void pressRegaButton() {
        super.element("//button[@type='submit']")
                .click();
    }
//

    private String input_code() {
        return "//input[@name='code']";
    }

    public void sendCode(String codeSms) {
        super.sendKeysToClear(codeSms, this.input_code());
    }

    public boolean w_InputCode_vis(int time) {
        return super.wait_visible(time, this.input_code());
    }
//    

    public void pressButtonConfirm() {
        super.element("//button[text()='Подтвердить']")
                .click();
    }

    public boolean w_IncorrectCodeError_vis(int time) {
        String incorrect_code_error = "//div[@class='error' and text()='Неверный код подтверждения']";
        return super.wait_visible(time, incorrect_code_error);
    }

    public boolean w_ConfirmMessage_vis(int time) {
        String confirm_message = "//div[normalize-space(text()='Спасибо, теперь вы зарегистрированы на ')]";
        return super.wait_visible(time, confirm_message);
    }
}
