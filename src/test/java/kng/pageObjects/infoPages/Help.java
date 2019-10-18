package kng.pageObjects.infoPages;

import static kng.EnvironmentDomain.DOMAIN;
import kng.driver.Obj_WebElements;

public class Help extends Obj_WebElements {

    public Help() {
    }

    public static String getUrl() {
        return DOMAIN + "/help";
    }

    public boolean w_Title(int timeSec) {
        return super.wait_titleContains(timeSec,
                "Центр поддержки KUPIVIP");
    }
//----------------------------------------------------------------------------------------------

    public String getTextContent() {
        return super.element("//*[@class='wrapper']")
                .getText();
    }
}
