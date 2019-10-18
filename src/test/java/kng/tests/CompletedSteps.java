package kng.tests;

import kng.driver.DriverTools;
import kng.http.OrdersStatusService;
import kng.pageObjects.MainPage;
import kng.pageObjects.RegLoginPopUp;
import static kng.EnvironmentDomain.DOMAIN;

public class CompletedSteps extends DriverTools {

    private final RegLoginPopUp LOGIN_MODAL;
    private final MainPage HEADER;
    private final OrdersStatusService SERVICE;
//------------------------------------------------------------------------

    public CompletedSteps() {
        LOGIN_MODAL = new RegLoginPopUp();
        HEADER = new MainPage();
        SERVICE = new OrdersStatusService();
    }
//------------------------------------------------------------------------

    public boolean orderCancel(String orderNumber) {
        return SERVICE.setOrderStatusCancel(orderNumber);
    }

    public boolean firstStageRegistration(String mail, String pass, boolean pageRefresh) {
        super.driverGet(DOMAIN);
        HEADER.clickLoginLinkPerAccountIconsList();
        LOGIN_MODAL.clickInsetOfRegistration();
        LOGIN_MODAL.checkOferta();
        LOGIN_MODAL.sendRegaMail(mail);
        LOGIN_MODAL.sendRegaPass(pass);
        LOGIN_MODAL.pressFirstStepRegistration();

        if (LOGIN_MODAL.w_SecondStepButton_vis(10) != true) {
            return false;
        }

        if (pageRefresh == true) {
            super.refreshPage();
        }
        return true;
    }

    public boolean authorization(String name, String pass) {
        super.driverGet(DOMAIN);
        HEADER.clickLoginLinkPerAccountIconsList();
        LOGIN_MODAL.sendLoginMail(name);
        LOGIN_MODAL.sendLoginPass(pass);
        LOGIN_MODAL.pressLogin();
        return LOGIN_MODAL.w_LoginModal_inVis(7);
    }
}
