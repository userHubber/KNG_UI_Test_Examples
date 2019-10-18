package kng.pageObjects;

import kng.driver.Obj_WebElements;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class RegLoginPopUp extends Obj_WebElements {

    public RegLoginPopUp() {
    }
//------------------------------------------------------------------------
//Login-------------------------------------------------------------------

//
    private String login_modal() {
        return "//*[@id='authorization-new']//div[@class='modal-content']";
    }

    public boolean w_LoginModal_vis(int time) {
        return super.wait_visible(time, this.login_modal());
    }

    public boolean w_LoginModal_inVis(int time) {
        return super.wait_inVisible(time, this.login_modal());
    }
//    

    public boolean w_ReviewMessage_vis(int time) {
        String message = "//*[@id='login-pane']//div[text()="
                + "'Войдите или зарегистрируйтесь для того чтобы написать отзыв']";
        return super.wait_visible(time, message);
    }

    public boolean w_CaptchaError_vis(int time) {
        String error
                = "//div[normalize-space(text())='Превышен лимит попыток, введите reCAPTCHA']";
        return super.wait_visible(time, error);
    }

    public boolean w_CaptchaBlock_vis(int time) {
        return super.wait_visible(time, "//*[@id='g-recaptcha']");
    }

    public void sendLoginMail(String name) {
        String input = "//*[@id='user-name']";
        super.wait_visible(5, input);
        super.sendKeysToClear(name, input);
    }

    public void sendLoginPass(String pass) {
        super.sendKeysToClear(pass, "//*[@id='password']");
    }

//    
    private String login_button() {
        return "//*[@id='login-submit']";
    }

    public void pressLogin() {
        super.element(this.login_button())
                .click();
    }

    public boolean w_LoginButton_vis(int time) {
        return super.wait_visible(time, login_button());
    }
//  

//FirstRega--------------------------------------------------------------
    public void clickInsetOfRegistration() {
        super.element("//*[@id='authorization-new']//a[text()='Регистрация']")
                .click();
    }

    public void checkOferta() {
        super.element("//*[@id='registration-pane']//input[@type='checkbox']")
                .click();
    }

    //
    private String first_step_button() {
        return "//button[contains (text(), 'Зарегистрироваться')]";
    }

    public void pressFirstStepRegistration() {
        super.element(this.first_step_button())
                .click();
    }

    public boolean w_FirstStepButton_vis(int time) {
        return super.wait_visible(time, this.first_step_button());
    }

    public boolean w_FirstStepButton_inVis(int time) {
        return super.wait_inVisible(time, this.first_step_button());
    }
//    

    public void sendRegaMail(String mail) {
        super.sendKeysToClear(mail, "//*[@id='email']");
    }

    public void sendRegaPass(String pass) {
        super.sendKeysToClear(pass, "//*[@id='pw']");
    }

    public boolean w_RegaMailIncorrectError_vis(int time) {
        String error = "//*[text()='Пожалуйста, введите правильный адрес e-mail']";
        return super.wait_visible(time, error);
    }

    public boolean w_RegaMailEarlierError_vis(int time) {
        String error = "//div[text()='Такой e-mail уже зарегистрирован']";
        return super.wait_visible(time, error);
    }

    public boolean w_RegaPassShortError_vis(int time) {
        String error = "//div[text()='Пароль должен содержать не менее 4 символов']";
        return super.wait_visible(time, error);
    }

    public boolean w_RegaPassInvalidSymbolError_vis(int time) {
        String error = "//div[text()='В пароле недопустимый символ']";
        return super.wait_visible(time, error);
    }

    public void sendName(String name) {
        super.sendKeysToClear(name, "//*[@id='registration-step2-name']");
    }

    public void sendSurname(String surname) {
        super.sendKeysToClear(surname, "//*[@id='registration-step2_lastname']");
    }

    public void sendPhone(String phone) {
        super.sendKeysToClear(phone, "//*[@id='registration-phone']");
    }

//    
    private String second_step_button() {
        return "//*[@id='register-step2']";
    }

    public void pressSecondStepRegistration() {
        super.element(this.second_step_button())
                .click();
    }

    public boolean w_SecondStepButton_vis(int time) {
        return super.wait_visible(time, this.second_step_button());
    }

    public boolean w_SecondStepButton_inVis(int time) {
        return super.wait_inVisible(time, this.second_step_button());
    }
//    

    public void clickLinkToMissSecondStep() {
        super.element("//*[text()='Пропустить']")
                .click();
    }

}
