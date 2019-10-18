package kng.pageObjects.admin;

import kng.driver.Obj_WebElements;
import java.awt.event.KeyEvent;
import static kng.EnvironmentDomain.DOMAIN;
/** 
 * <b>context:</b>
 * <br>press - button
 * <br>send -  input
 * <br>check - checkBox
*/
public class Promos extends Obj_WebElements {

    public Promos() {
    }
//---------------------------------------------------------------------------

    public static String getDisposableUrl() {
        return DOMAIN + "/protected/admin/marketing/promos/disposable/";
    }

    public static String getReusableUrl() {
        return DOMAIN + "/protected/admin/marketing/promos/reusable/";
    }
//---------------------------------------------------------------------------

    public String getCodeFromAllCouponsPage(String codeContains) {
        return super.element("//*[contains (text(), '" + codeContains + "')]")
                .getText();
    }

    public boolean w_PrefixEmptyError_vis(int time) {
        return super.wait_visible(time, "//*[text()='Длинна префикса не может быть менее 3 символов.']");
    }

    public boolean w_PrefixIncorrectError_vis(int time) {
        return super.wait_visible(time, "//*[text()='Префикс содержит некорректные символы.']");
    }

    public boolean w_SuffixIncorrectError_vis(int time) {
        return super.wait_visible(time, "//*[text()='Суффикс содержит некорректные символы.']");
    }

    public void pressNewDisposableCoupon() {
        super.element("//*[text()='Новый одноразовый промокод']")
                .click();
    }

    public void pressNewReusableCoupon() {
        super.element("//*[text()='Новый многоразовый промокод']")
                .click();
    }

    public void selectDisposableCouponType(String couponType) {
        String disposable_coupon_drop = "//*[@id='type']";
        super.element(disposable_coupon_drop).click();
        String disposable_coupon_type_in_drop_list = "//*[@id='type']/option[@value='" + couponType + "']";
        super.element(disposable_coupon_type_in_drop_list).click();
    }

    public void selectReusableCouponType(String couponType) {
        String reusable_coupon_drop = "//*[@id='couponType']";
        super.element(reusable_coupon_drop).click();
        String reusable_coupon_type_in_drop_list = "//*[@id='couponType']/option[@value='" + couponType + "']";
        super.element(reusable_coupon_type_in_drop_list).click();
    }

    public void selectCostCentre(String costType) {
        String cost_centre_drop = "//*[@id='costCentre']";
        super.element(cost_centre_drop).click();
        String cost_centre_in_drop_list = "//*[@id='costCentre']/option[@value='" + costType + "']";
        super.element(cost_centre_in_drop_list).click();
    }

    public void sendStartDate(String time) {
        super.element("//*[@id='start']")
                .sendKeys(time);
        this.keyCombinationToEnterTimeInDate();
    }

    public void sendEndDate(String time) {
        super.element("//*[@id='end']")
                .sendKeys(time);
        this.keyCombinationToEnterTimeInDate();
    }

    public void sendCoveragePercent(String percent) {
        super.sendKeysToClear(percent, "//*[@id='coverage']");
    }

    public void sendNominalDisposableCoupon(String nominlValue) {
        super.sendKeysToClear(nominlValue, "//*[@id='initialValue']");
    }

    public void sendNominalReusableCoupon(String nominlValue) {
        super.sendKeysToClear(nominlValue, "//*[@id='bonusAmount']");
    }

    public void sendUserDescription(String description) {
        super.sendKeysToClear(description, "//*[@id='description']");
    }

    public void sendComment(String comment) {
        super.sendKeysToClear(comment, "//*[@id='comment']");
    }

    public void sendCouponCount(String number) {
        super.sendKeysToClear(number, "//*[@id='count']");
    }

    public void sendCouponPrefix(String prefix) {
        super.sendKeysToClear(prefix, "//*[@id='prefix']");
    }

    public void sendCouponSuffix(String suffix) {
        super.sendKeysToClear(suffix, "//*[@id='suffix']");
    }

    public void sendCouponLength(String length) {
        super.sendKeysToClear(length, "//*[@id='length']");
    }

    public void sendReusablePromoCode(String couponCode) {
        super.sendKeysToClear(couponCode, "//*[@id='value']");
    }

    public void pressCreateDisposableCoupon() {
        super.element("//button[text()='Создать купоны с промокодами']")
                .click();
    }

    public void pressCreateReusableCoupon() {
        super.element("//button[text()='Создать промокод']")
                .click();
    }

//######
    private void keyCombinationToEnterTimeInDate() {
        ROBOT.keyPress(KeyEvent.VK_TAB);
        ROBOT.keyPress(KeyEvent.VK_0);
        ROBOT.keyPress(KeyEvent.VK_TAB);
        ROBOT.keyPress(KeyEvent.VK_0);
        ROBOT.keyRelease(KeyEvent.VK_0);
    }
}
