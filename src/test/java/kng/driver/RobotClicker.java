package kng.driver;

import java.awt.AWTException;
import java.awt.Robot;

class RobotClicker {

    RobotClicker() {
    }
    static Robot ROBOT = setRobot();

    private static Robot setRobot() {
        try {
            ROBOT = new Robot();
            return ROBOT;
        } catch (AWTException ignore) {
        }

        return null;
    }

}
