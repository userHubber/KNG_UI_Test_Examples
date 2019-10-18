package kng.pageObjects;

import kng.driver.Obj_WebElements;
import static kng.EnvironmentDomain.DOMAIN;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 * <br>check - checkBox
 * <br>review - product review
 * <br>order - order review
 */
public class OrderReview extends Obj_WebElements {

    public OrderReview() {
    }
//--------------------------------------------------------------

    public static String getUrl(String orderNumber) {
        return DOMAIN + "/orderReview/" + orderNumber;
    }
//--------------------------------------------------------------

    public boolean w_ConfirmSuccesMessage_vis(int time) {
        String message = "//p[text()='Спасибо большое! Отзыв принят на модерацию']";
        return super.wait_visible(time, message);
    }

//order review  
    public void pressOrderReviewExpand() {
        super.element("//a[@data-elem='reviews-link']")
                .click();
    }

    public void sendOrderToTextArea(String review) {
        super.sendKeysToClear(review, "//div[@class='message']//textarea");
    }

    public void pressOrderSend() {
        ROBOT.delay(300);
        String button
                = "//div[@data-elem='revieworder-form']//div[@class='button-block']//button[@type='submit']";
        super.element(button).click();
    }

    public void selectOrderExelentProcessRating() {
        ROBOT.delay(300);
        super.element("//label[@for='mark-5-CONVENIENCE']")
                .click();
        ROBOT.delay(300);
    }

    public void selectOrderExelentQualityRating() {
        ROBOT.delay(300);
        super.element("//label[@for='mark-5-QUALITY']")
                .click();
        ROBOT.delay(300);
    }

    public void selectOrderExelentDeliveryRating() {
        ROBOT.delay(300);
        super.element("//label[@for='mark-5-DELIVERY']")
                .click();
        ROBOT.delay(300);
    }

    public void selectOrderBadDeliveryRating() {
        ROBOT.delay(300);
        super.element("//label[@for='mark-2-DELIVERY']")
                .click();
        ROBOT.delay(300);
    }

    public boolean w_OrderRatingsAbsentError_vis(int time) {
        String error = "//div[text()='Нет ни одной оценки']";
        return super.wait_visible(time, error);
    }

    public boolean w_OrderReturnedCauseAbsentError_vis(int time) {
        String error = "//div[text()='Нужно указать причину отмены']";
        return super.wait_visible(time, error);
    }

    public boolean w_OrderAccurateReturnedCauseAbsentError_vis(int time) {
        String error = "//div[text()='Уточните причину отмены']";
        return super.wait_visible(time, error);
    }

    public boolean w_OrderCommentAbsentError_vis(int time) {
        String error = "//div[text()='Пожалуйста, оставьте комментарий']";
        return super.wait_visible(time, error);
    }

//product (group) peview    
    public void sendReviewToTextArea(String review) {
        super.sendKeysToClear(review, "//div[@class='message big']//textarea");
    }

    public void pressReviewSend() {
        String button
                = "//div[@class='list-order']//div[@class='button-block']//button[@type='submit']";
        super.element(button).click();
    }

    public void checkReviewSizeGood() {
        ROBOT.delay(200);
        super.element("//label[contains (text(), 'Размер подошёл')]")
                .click();
    }

    public void selectReviewRatingExelent() {
        String rating = "//label[@for='mark-5-0']";
        ROBOT.delay(300);
        super.element(rating).click();
        ROBOT.delay(300);
    }

    public void checkSizeForTypeOfProblemReview() {
        ROBOT.delay(300);
        super.element("//div[@class='radio']//label[text()='Проблема с размером']")
                .click();
    }

    public void checkSmallSizeForTypeOfProblemReview() {
        ROBOT.delay(300);
        super.element("//div[@class='radio']//label[text()='Размер мал']")
                .click();
    }

}
