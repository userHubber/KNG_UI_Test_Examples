package kng.tests.loginAndRegistration;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import kng.driver.DriverTools;
import kng.pageObjects.account.Personal;
import kng.pageObjects.RegLoginPopUp;
import kng.tests.CompletedSteps;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Case_SecondStage extends DriverTools {

    private Data_loginAndRegistration case_data;
    private CompletedSteps step;
    private RegLoginPopUp login$reg_modal;
    private Personal acc_person;
    private String validMail;
//================================================================  

    @BeforeTest(alwaysRun = true)
    public void preconditions() {
        System.out.println("РЕГИСТРАЦИЯ ВТОРОЙ ЭТАП(ПОЗИТИВ/НЕГАТИВ)");
        super.driverAssign();
        case_data = new Data_loginAndRegistration();
        step = new CompletedSteps();
        login$reg_modal = new RegLoginPopUp();
        acc_person = new Personal();
    }

    @BeforeClass(alwaysRun = true)
    public void condition() {
        validMail = case_data.getValidMail();
        step.firstStageRegistration(validMail, case_data.VALID_PASSWORD, false);
    }

    @Test(priority = 1, groups = {"dev", "pre", "prod"})
    public void case_SecondStageEmptyName() {//имя обязательно, остальные поля - нет
        login$reg_modal.pressSecondStepRegistration();
        assertFalse(login$reg_modal.w_SecondStepButton_inVis(2), "Second stage modal invisible");
    }

    @Test(priority = 3, groups = {"dev", "pre", "prod"})
    public void case_SecondStagePosistive() {
        login$reg_modal.sendName(case_data.FIRST_NAME);
        login$reg_modal.sendSurname(case_data.SECOND_NAME);
        login$reg_modal.sendPhone(case_data.getPhone());
        login$reg_modal.pressSecondStepRegistration();
        assertTrue(login$reg_modal.w_SecondStepButton_inVis(12), "Second stage modal visible");
    }

    @Test(priority = 5, groups = {"dev", "pre", "prod"})
    public void case_SecondStageAccPersonalDataControl() {
        super.refreshPage();//time for adaptation Account pers. data
        super.driverGet(Personal.getUrl());
        assertTrue(acc_person.w_Title(10), "Title not contain");
        assertTrue(acc_person.w_SavedNameInInputContain(5, case_data.SAVED_NAME_ACC),
                "Personal data contain name: " + acc_person.getSavedName()
                + "\n expected: " + case_data.SAVED_NAME_ACC);
        String savedMail = acc_person.getSavedMail();
        assertTrue(savedMail.equals(validMail),
                "Personal data contain mail: " + savedMail
                + "\n expected: " + validMail);

        String expectedPhone = case_data.getSavedPhone();
        String currentPhone = acc_person.getSavedPhone();

        assertTrue(acc_person.w_SavedPhoneInInputContain(5, expectedPhone),
                "Personal data contain phone: " + currentPhone
                + "\n expected: " + expectedPhone);
    }

    @Test(dependsOnMethods = "case_SecondStagePosistive", groups = {"dev", "pre", "prod"})
    public void case_deleteAccount() {
        super.driverGet(Personal.getUrl());
        acc_person.clickAccountDeletingLink();
        acc_person.clickConfirmAccountDeletingLink();
        acc_person.pressConfirmDeleteAccounAndAcceptAlert(7);
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser() {
        super.driverCloseQuit();
    }
}
