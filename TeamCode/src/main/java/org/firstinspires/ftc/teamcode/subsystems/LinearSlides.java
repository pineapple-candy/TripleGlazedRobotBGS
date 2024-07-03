package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class LinearSlides {

    private static final DcMotorSimple.Direction Direction = DcMotorSimple.Direction.REVERSE;

    private static PIDController pid = new PIDController(1,0,0);

    private static DcMotor slideMotor;


    public static void initialiseSlide(DcMotor motor) { // hardwareMap.get(DCMotor.class,"Name")
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(0);
        motor.setDirection(Direction);

        pid.setSetPoint(1000);
        slideMotor = motor;

    }

    public static void updateSlide() {
        double output = pid.calculate(
                slideMotor.getCurrentPosition()
        );

        slideMotor.setPower(output);
    }

    private static void setTarget(double target) {
        pid.setSetPoint(target);
    }

    public static void setActive(boolean target) {
        if (gamepad1.left_bumper) {
            LinearSlides.setTarget(2000); // top or smthn
        }
        LinearSlides.updateSlide();
    }

}
