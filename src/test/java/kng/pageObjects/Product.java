package kng.pageObjects;

import kng.driver.Obj_WebElements;
import kng.helpers.Utils;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 */
public class Product extends Obj_WebElements {

    public Product() {
    }
//--------------------------------------------------------------

    public void pressAddToBasket() {
        super.element("//*[@id='addToBasket']").click();
    }

    public void pressSize(String sizeVariantCode) {
        if (!sizeVariantCode.contains("OS")) {
            String sizeButton = "//*[@id='toBasket']//label[@data-value='" + sizeVariantCode + "']";
            super.element(sizeButton).click();
        }
    }

    public int getPrice() {
        String price = super.element("//div[@itemprop='offers']//span")
                .getText();
        return Utils.toInt(price);
    }

    public boolean pressBrandSubscribe(int time, boolean isSubscribed) {
        String button = "//button[@data-js='brand-subscribe']";
        super.element(button).click();

        if (isSubscribed) {
            return super.wait_containAttribute(time, "data-subscribed", "true", button);
        }

        return super.wait_containAttribute(time, "data-subscribed", "false", button);
    }

    public boolean pressFavorite(int time, boolean isSubscribed) {
        String icon = "//button[@data-js='product-subscribe']";
        super.element(icon).click();
        if (isSubscribed) {
            return super.wait_containAttribute(time, "data-subscribed", "true", icon);
        }

        return super.wait_containAttribute(time, "data-subscribed", "false", icon);
    }

    public void pressReviewWrite() {
        super.element("//*[@id='open-review']")
                .click();
    }

    public boolean w_RatingAbsentAndReviewShortError_vis(int time) {
        String error = "//*[text()='Укажите оценку. Также текст отзыва слишком короткий']";
        return super.wait_visible(time, error);
    }

    public boolean w_RatingExelentAndReviewShortError_vis(int time) {
        String error = "//*[text()='Слишком короткий текст']";
        return super.wait_visible(time, error);
    }

    public boolean w_RaitingBadAndReviewShortError_vis(int time) {
        String error = "//*[text()='Слишком короткий текст. Tакже укажите причину низкой оценки']";
        return super.wait_visible(time, error);
    }

    public boolean w_RaitingExelentAndReviewCorrectWithoutSizeError_vis(int time) {
        String error = "//*[text()='Укажите соответствие размера']";
        return super.wait_visible(time, error);
    }

    public boolean w_ReviewCorrectAndRatingAbsentError_vis(int time) {
        String error = "//*[text()='Укажите оценку (количество звёзд)']";
        return super.wait_visible(time, error);
    }

    public boolean w_ReviewSuccesMessage_vis(int time) {
        String message = "//*[text()='Спасибо за ваш отзыв!']";
        return super.wait_visible(time, message);
    }

    public boolean w_NoCouponMessage_vis(int time) {
        String message = "//*[text()='Купоны, промокоды и персональная скидка на этот товар не действуют.']";
        return super.wait_visible(time, message);
    }

    public void checkReviewSizeGood() {
        ROBOT.delay(200);
        super.element("//label[contains (text(), 'Размер подошёл')]")
                .click();
    }

    public void selectReviewRatingExelent() {
        ROBOT.delay(500);
        super.element("//*[@id='review-form']//label[@for='mark-5']")
                .click();
        ROBOT.delay(300);
    }

    public void selectReviewRatingBad() {
        ROBOT.delay(500);
        super.element("//*[@id='review-form']//label[@for='mark-2']")
                .click();
        ROBOT.delay(300);
    }

//
    private String another_reason_of_bad_rating() {
        return "//label[@for='reason4']";
    }

    public void checkAnotherReasonPerBadRating() {
        ROBOT.delay(200);
        super.element(this.another_reason_of_bad_rating())
                .click();
    }

    public boolean w_AnotherReasonPerBadRatingCheckbox(int time) {
        return super.wait_visible(time, this.another_reason_of_bad_rating());
    }
//

//
    private String review_textarea() {
        return "//*[@id='review']";
    }

    public void sendReviewToTextArea(String review) {
        super.element(this.review_textarea())
                .sendKeys(review);
    }

    public boolean w_ReviewTextArea_vis(int time) {
        return super.wait_visible(time, this.review_textarea());
    }
//    

    public void pressReviewSend() {
        String button = "//*[@id='add-review']";
        super.wait_clickable(5, button);
        super.element(button).click();
    }

//
    private String price_with_discounts() {
        return "//*[@class='price-with-discounts']";
    }

    public void clickPriceWithDiscounts() {
        super.element(this.price_with_discounts())
                .click();
    }

    public int getDiscountsAmount() {
        String priceText = super.element(this.price_with_discounts())
                .getText();
        return Utils.toInt(priceText);
    }
//

    public int getCardDiscountAmount() {
        String amount = "//*[@class='discounts-breakdown']//span[contains (text(),'По предоплате')]/preceding-sibling::*";
        String amountText = super.element(amount).getText();
        return Utils.toInt(amountText);
    }

    public int getPersonalDiscountAmount() {
        String amount = "//*[@class='discounts-breakdown']//span[contains (text(),'С персональной скидкой')]/preceding-sibling::*";
        String amountText = super.element(amount).getText();
        return Utils.toInt(amountText);
    }

    public int getCouponDiscountAmount() {
        String amount = "//*[@class='discounts-breakdown']//span[contains (text(),'По промокоду')]/preceding-sibling::*";
        String amountText = super.element(amount).getText();
        return Utils.toInt(amountText);
    }

//    
    public boolean w_TardisFrame_vis(int time) {
        String frame = "//*[@id='TardisWidget']";
        return super.wait_visible(time, frame);
    }

    public boolean pressTardis() {
        String tardButton = "//*[@id='Tardis']//*[text()='Подобрать размер']";
        if (super.find(tardButton)) {
            super.element(tardButton).click();
            return true;
        }
        return false;
    }

}
