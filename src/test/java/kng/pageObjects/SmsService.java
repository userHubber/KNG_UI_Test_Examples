package kng.pageObjects;

import kng.driver.Obj_WebElements;

public class SmsService extends Obj_WebElements {

    public SmsService() {
    }

//--------------------------------------------------------------
    public static String getUrl() {
        return "https://qealty.ru/";
    }
//--------------------------------------------------------------

    public boolean w_ServicePhones_vis() {
        return super.wait_visible(7, "//button[text()='Виртуальные номера']");
    }
//

    private String sms_codes() {
        return "//*[contains(text(),'KUPIVIP.RU')]/following-sibling ::*";
    }

    private String phone() {
        return "//*[@id='test3']/div[@class='phone']";
    }

    public String getPhone() {
        String phoneText = super.element(this.phone())
                .getText();
        String phoneNumber = phoneText.replaceAll("[^0-9]", "").substring(1);
        return phoneNumber;
    }

    public int getKupivipSMSCountFromSelectedPhone() {
        super.element(this.phone())
                .click();
        ROBOT.delay(2000);
        return super.getList(this.sms_codes())
                .size();
    }

    public String getSmsCode(int actualCount) {
        int currentSmsCount = actualCount;
        int controlSmsCount;

        for (int i = 0; i < 11; i++) {
            controlSmsCount = super.getList(this.sms_codes())
                    .size();
            if (controlSmsCount > currentSmsCount) {
                String sms = super.element(this.sms_codes())
                        .getText();
                String code = sms.replaceAll("[^0-9]", "");
                return code;
            } else {
                ROBOT.delay(1500);
            }
        }

        return null;
    }

}
