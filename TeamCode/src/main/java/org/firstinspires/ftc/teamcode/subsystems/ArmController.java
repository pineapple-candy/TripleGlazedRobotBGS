package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;

public class ArmController {

    private static CRServo leftServo;
    private static CRServo rightServo;

    public static void initialiseArmServo(CRServo AleftServo, CRServo ArightServo) {
        leftServo = AleftServo;
        rightServo = ArightServo;
    }

    public static void armSuck(boolean aButton, boolean bButton) {
        if (aButton) {
            leftServo.setPower(1);
            rightServo.setPower(-1);
        } else if (bButton) {
            leftServo.setPower(-1);
            rightServo.setPower(1);
        } else {
            leftServo.setPower(0);
            rightServo.setPower(0);
        }

    }
}
