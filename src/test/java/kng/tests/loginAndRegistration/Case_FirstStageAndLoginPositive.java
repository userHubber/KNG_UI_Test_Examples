package kng.tests.loginAndRegistration;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import java.util.ArrayList;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import kng.driver.DriverTools;
import kng.pageObjects.account.LeftMenu;
import kng.pageObjects.account.Personal;
import kng.pageObjects.MainPage;
import kng.pageObjects.RegLoginPopUp;
import static kng.EnvironmentDomain.DOMAIN;

public class Case_FirstStageAndLoginPositive extends DriverTools {

    private Data_loginAndRegistration case_data;
    private RegLoginPopUp login$reg_modal;
    private Personal acc_person;
    private MainPage header;
    private LeftMenu acc_left_menu;
    private String validMail;

    @BeforeTest(alwaysRun = true)
    public void preconditions() {
        System.out.println("РЕГИСТРАЦИЯ(ПОЗИТИВ/НЕГАТИВ) # ЛОГИН/ЛОГАУТ");
        super.driverAssign();
        case_data = new Data_loginAndRegistration();
        login$reg_modal = new RegLoginPopUp();
        acc_person = new Personal();
        header = new MainPage();
        acc_left_menu = new LeftMenu();
    }

    @BeforeClass(alwaysRun = true)
    public void conditions() {
        validMail = case_data.getValidMail();
    }

//helper------------------------------------------------------
    private void conditionModalOpened() {
        super.driverGet(DOMAIN);
        super.driverClear();
        super.refreshPage();
        header.clickLoginLinkPerAccountIconsList();
        login$reg_modal.clickInsetOfRegistration();
        login$reg_modal.checkOferta();
    }
//------------------------------------------------------------

    @Test(priority = 1, groups = {"dev", "pre"})//первый этап, негатив, без подтверждения оферты
    public void case_firstStageNegativeWithoutOferta() {
        super.driverGet(DOMAIN);
        header.clickLoginLinkPerAccountIconsList();
        login$reg_modal.clickInsetOfRegistration();
        login$reg_modal.sendRegaMail(case_data.getValidMail());
        login$reg_modal.sendRegaPass(case_data.VALID_PASSWORD);
        login$reg_modal.pressFirstStepRegistration();
        assertFalse(login$reg_modal.w_FirstStepButton_inVis(2), "First stage modal invisible");
    }

    @Test(priority = 2, groups = {"dev", "pre"})//первый этап, негатив, пустые поля
    public void case_firstStageNegativeEmptyFields() {
        this.conditionModalOpened();
        login$reg_modal.pressFirstStepRegistration();
        assertFalse(login$reg_modal.w_FirstStepButton_inVis(2), "First stage modal invisible");
    }

    @Test(priority = 3, groups = {"dev", "pre"})//первый этап, негатив, невалидный майл домен
    public void case_firstStageNegativeMailIncorrectDomain() {
        this.conditionModalOpened();
        login$reg_modal.sendRegaMail(case_data.MAIL_INCORRECT_DOMAIN);
        login$reg_modal.sendRegaPass(case_data.VALID_PASSWORD);
        login$reg_modal.pressFirstStepRegistration();
        assertTrue(login$reg_modal.w_RegaMailIncorrectError_vis(3), "Error: incorrect mail not visible");
    }

    @Test(priority = 5, groups = {"dev", "pre"})//первый этап, негатив, пароль менее 4 символов
    public void case_firstStageNegativePasswordShort() {
        this.conditionModalOpened();
        login$reg_modal.sendRegaMail(case_data.getValidMail());
        login$reg_modal.sendRegaPass(case_data.PASSWORD_SHORT);
        login$reg_modal.pressFirstStepRegistration();
        assertTrue(login$reg_modal.w_RegaPassShortError_vis(3), "Error: pass short not visible");
    }

    @Test(priority = 6, groups = {"dev", "pre"})//первый этап, негатив, пароль менее 4 символов
    public void case_firstStageNegativePasswordFailSymbol() {
        this.conditionModalOpened();
        login$reg_modal.sendRegaMail(case_data.getValidMail());
        login$reg_modal.sendRegaPass(case_data.PASSWORD_CYRILL_CONTAIN);
        login$reg_modal.pressFirstStepRegistration();
        assertTrue(login$reg_modal.w_RegaPassInvalidSymbolError_vis(3), "Error: pass invalid symbol not visible");
    }

    //Reg and depends 
    @Test(priority = 7, groups = {"dev", "pre", "prod"})//первый этап, позитив
    public void case_firstStagePosistive() {
        this.conditionModalOpened();
        login$reg_modal.sendRegaMail(validMail);
        login$reg_modal.sendRegaPass(case_data.VALID_PASSWORD);
        login$reg_modal.pressFirstStepRegistration();
        assertTrue(login$reg_modal.w_SecondStepButton_vis(10), "Second stage modal not visible");
    }

    @Test(dependsOnMethods = "case_firstStagePosistive",//кнопка Пропустить
            groups = {"dev", "pre", "prod"}, priority = 1)
    public void case_skipSecondStageButtonControl() {
        login$reg_modal.clickLinkToMissSecondStep();
        assertFalse(login$reg_modal.w_SecondStepButton_vis(1), "Second stage modal not visible");

    }

    @Test(dependsOnMethods = "case_firstStagePosistive",
            groups = {"dev", "pre", "prod"}, priority = 2)
    public void case_firstStageAccPersonalDataControl() {
        super.refreshPage();//time for adaptation Account pers. data
        super.driverGet(Personal.getUrl());
        assertTrue(acc_person.w_Title(10), "Title not contain");
        assertTrue(acc_person.w_SavedNameInInputContain(5, case_data.RESPECT_CLIENT),
                "Personal data contain name: " + acc_person.getSavedName()
                + "\n expected: " + case_data.RESPECT_CLIENT);
        String savedMail = acc_person.getSavedMail();
        assertTrue(savedMail.equals(validMail),
                "Personal data contain mail: " + savedMail
                + "\n expected: " + validMail);
    }

    @Test(dependsOnMethods = "case_firstStagePosistive",
            groups = {"dev", "pre"}, priority = 3)////первый этап, негатив, ранее зареганный в сиситеме mail
    public void case_firstStageNegativeMailEarlierRegister() {
        this.conditionModalOpened();
        login$reg_modal.sendRegaMail(validMail);
        login$reg_modal.sendRegaPass(case_data.VALID_PASSWORD);
        login$reg_modal.pressFirstStepRegistration();
        assertTrue(login$reg_modal.w_RegaMailEarlierError_vis(3), "Error: mail register earlier not visible");
    }

    @Test(dependsOnMethods = "case_firstStagePosistive",
            groups = {"dev", "pre", "prod"}, priority = 4)
    public void case_loginPositive() {
        super.driverGet(DOMAIN);
        super.driverClear();
        super.refreshPage();
        header.clickLoginLinkPerAccountIconsList();
        login$reg_modal.sendLoginMail(validMail);
        login$reg_modal.sendLoginPass(case_data.VALID_PASSWORD);
        login$reg_modal.pressLogin();
        assertTrue(login$reg_modal.w_LoginModal_inVis(7), "Login modal visible");
    }

    @Test(dependsOnMethods = "case_loginPositive",
            groups = {"dev", "pre", "prod"})
    public void case_logOut() {
        super.driverGet(Personal.getUrl());
        acc_left_menu.clickLogOut();
        assertTrue(header.waitUrl(5), "Not MainUrl");
        ArrayList<String> menuList = header.getHeaderAccountDropList();
        assertTrue(menuList.contains("Вход"), "Not MainUrl");
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser() {
        super.driverCloseQuit();
    }
}
