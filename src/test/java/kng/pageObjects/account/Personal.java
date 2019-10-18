package kng.pageObjects.account;

import kng.driver.Obj_WebElements;
import static kng.EnvironmentDomain.DOMAIN;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class Personal extends Obj_WebElements {

    public Personal() {
    }

//---------------------------------------------------------------------------
    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Личные данные");
    }

    public static String getUrl() {
        return DOMAIN + "/account/personal-data";
    }
//---------------------------------------------------------------------------    

    public String getSavedMail() {
        String email
                = "//*[@id='personal-data-form']//label[@class='control-label']/span";
        return super.element(email).getText();
    }
//

    private String input_name() {
        return "//*[@id='personal-data-name']";
    }

    public String getSavedName() {
        return super.element(this.input_name())
                .getAttribute("value");
    }

    public boolean w_SavedNameInInputContain(int time, String userName) {
        return super.wait_containAttribute(time, "value", userName, this.input_name());
    }
//   

    private String input_phone() {
        return "//*[@id='personal-data-phone']";
    }

    public String getSavedPhone() {
        return super.element(this.input_phone())
                .getAttribute("value");
    }

    public boolean w_SavedPhoneInInputContain(int time, String userPhone) {
        return super.wait_containAttribute(time, "value", userPhone, this.input_phone());
    }
//   

    public void clickAccountDeletingLink() {
        super.element("//a[text()='Удалить аккаунт']")
                .click();
    }

    public void clickConfirmAccountDeletingLink() {
        super.element("//a[text()='Хочу удалить аккаунт с сайта']")
                .click();
    }

    public boolean pressConfirmDeleteAccounAndAcceptAlert(int time) {
        super.element("//button[text()='Подтверждаю полное удаление']")
                .click();
        super.alertAccept();
        return super.wait_urlContains(time, "afterDelete");
    }
}
