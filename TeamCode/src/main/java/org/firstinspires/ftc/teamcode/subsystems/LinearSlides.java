package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.drm.DrmStore;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class LinearSlides {

    private static final DcMotor.Direction Direction = DcMotor.Direction.REVERSE;

    private static PIDController pid = new PIDController(0.62,0.4,0.04);

    private static DcMotor slideMotor;


    public static void initialiseSlide(DcMotor motor) { // hardwareMap.get(DCMotor.class,"Name")
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(0);
        motor.setDirection(Direction);

        pid.setSetPoint(0);
        slideMotor = motor;

    }

    public static void updateSlide() {
        double output = pid.calculate(
                slideMotor.getCurrentPosition()
        );

        slideMotor.setPower(output);
    }

    public static void setTarget(double target) {
        pid.setSetPoint(target);
    }

    public static void setActive(boolean target, double slideY) {
        double encoderPosition = slideMotor.getCurrentPosition();

        if (target) {
            setTarget(-860); // BOTTOM < initialise
            updateSlide();
        } else {
            if (encoderPosition > 20) {
                if (slideY > 0.25) {
                    slideMotor.setPower(-0.45 * slideY);
                } else {
                    slideMotor.setPower(0);
                }
            } else if (encoderPosition < -720) {
                if (encoderPosition < -840) {
                    if (slideY < -0.25) {
                        slideMotor.setPower(-0.33*slideY);
                    } else {
                        slideMotor.setPower(0);
                    }
                } else {
                    if (slideY > 0.25) {
                        slideMotor.setPower(-0.4* slideY);
                    } else if (slideY < 0.25) {
                        slideMotor.setPower(-0.33 * slideY);
                    } else {
                        slideMotor.setPower(0);
                    }
                }

            } else {
                if ((slideY) > 0.25) {
                    slideMotor.setPower(-0.45 * slideY);
                } else if (slideY < 0.25) {
                    slideMotor.setPower(-0.33*slideY);
                } else {
                    slideMotor.setPower(0);
                }
            }

        }
    }

    public static void resetEncoder(boolean rightBumper) {// <-- testing only
        if (rightBumper) {
            slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public static void resetEncoder() {
            slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public static String getDebug() {
        return "Slide position: " + slideMotor.getCurrentPosition();
    }

}

