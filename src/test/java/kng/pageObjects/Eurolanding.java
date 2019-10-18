package kng.pageObjects;

import kng.driver.Obj_WebElements;

/**
 * <b>context:</b>
 * <br>press - button
 * <br>send - input
 */
public class Eurolanding extends Obj_WebElements {

    public Eurolanding() {
    }
//---------------------------------------------------------------------------

    public boolean w_Title(int time) {
        return super.wait_titleContains(time, "Паспортные данные");
    }
//---------------------------------------------------------------------------

//#####
    private void sendInfoToInput(String inputSpecial, String userInfo) {
        super.sendKeysToClear(userInfo, "//*[@id='" + inputSpecial + "']");
    }
//#####

    public void sendName(String firstName) {
        this.sendInfoToInput("name", firstName);
    }

    public void sendSurname(String secondName) {
        this.sendInfoToInput("surname", secondName);
    }

    public void sendPatronomic(String thirdName) {
        this.sendInfoToInput("patron", thirdName);
    }

    public void sendPassportNumber(String passNum) {
        this.sendInfoToInput("passnum", passNum);
    }

    public void sendPassportDepartment(String passDepartment) {
        this.sendInfoToInput("passdepartment", passDepartment);
    }

    public void sendPassportDateIssue(String passIssueDate) {
        this.sendInfoToInput("passdate", passIssueDate);
    }

    public void sendBirthday(String birthDay) {
        this.sendInfoToInput("birthday", birthDay);
    }

    public void sendINN(String _inn) {
        this.sendInfoToInput("inn", _inn);
    }

    public void pressSubmit() {
        String submit = "//button[@type='submit']";
        super.wait_clickable(5, submit);
        super.element(submit).click();
    }

    public void clickLeaveLandingAfterCheckout() {
        String leave = "//a[@data-elem='dismiss']";
        super.wait_clickable(5, leave);
        super.element(leave).click();
    }

    public boolean w_ConfirmSuccesMessage(int time) {
        return super.wait_presence(time, "//div[@class='success']");
    }

}
