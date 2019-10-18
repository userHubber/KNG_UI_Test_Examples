package kng.pageObjects.admin;

import kng.driver.Obj_WebElements;
import static kng.EnvironmentDomain.DOMAIN;

public class ReviewsWork extends Obj_WebElements {

    public ReviewsWork() {
    }
//---------------------------------------------------------------------------

    public static String getUrl() {
        return DOMAIN + "/protected/admin/reviews/work";
    }
//---------------------------------------------------------------------------

    public boolean findReview(String review) {
        return super.find("//div[contains (text(), '" + review + "')]");
    }
}
