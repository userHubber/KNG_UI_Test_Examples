package kng.tests.loginAndRegistration;

import java.util.ArrayList;
import kng.helpers.Utils;

public class Data_loginAndRegistration {

    public Data_loginAndRegistration() {
    }

    private final Utils SUPPLIER = new Utils();

    final String MAIL_INCORRECT_DOMAIN = "12@incorrect";
    final String VALID_PASSWORD = "121212";
    final String PASSWORD_SHORT = "123";
    final String PASSWORD_CYRILL_CONTAIN = "фыва";
    final String FIRST_NAME = "Карл";
    final String SECOND_NAME = "Карлов";
    final String SAVED_NAME_ACC = "Карлов Карл";
    final String RESPECT_CLIENT = "уважаемый клиент";

    String getSavedPhone() {
        return SUPPLIER.getSavedFormatPhoneByAccount(this.getPhone());
    }

    String getPhone() {
        return SUPPLIER.getGeneratedPhone();
    }

    String getValidMail() {
        return SUPPLIER.getGeneratedMail();
    }

    ArrayList<String> getListPasswordsForNegativeLogin() {
        ArrayList<String> variantsPassForNegativeLogin = new ArrayList<>();
        variantsPassForNegativeLogin.add(0, "");
        variantsPassForNegativeLogin.add(1, "123");
        variantsPassForNegativeLogin.add(2, "фыва");
        variantsPassForNegativeLogin.add(3, "1234567890");
        variantsPassForNegativeLogin.add(4, "dghdfgh");
        return variantsPassForNegativeLogin;
    }

}
