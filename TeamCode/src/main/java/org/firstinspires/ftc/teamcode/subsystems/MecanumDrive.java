package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class MecanumDrive {

    private final static double speedConstant =0.7;
    private static DcMotor leftBack;
    private static DcMotor leftFront;
    private static DcMotor rightFront;
    private static DcMotor rightBack;

    private static double encoderTicks = 537.6;

    private static boolean fieldOriented = false;

    public static void initialiseMotors(DcMotor AleftBack, DcMotor AleftFront, DcMotor ArightFront, DcMotor ArightBack) {
        leftBack = AleftBack;
        leftFront = AleftFront;
        rightBack = ArightBack;
        rightFront = ArightFront;

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public static void runMotors(double Ay, double Ax, double Aturn, boolean XButton, boolean YButton) {
        if (YButton && XButton) {
            IMUController.resetIMU();
            fieldOriented = true;
        } else if (XButton) {
            fieldOriented = false;
        }

        if ((Math.abs(Ax) > 0.05) || (Math.abs(Ay) > 0.05) || (Math.abs(Aturn) > 0.05)) {
            if (fieldOriented) {
                double y = -Ay; // Remember, Y stick value is reversed
                double x = Ax;
                double rx = Aturn;

                double botHeading = IMUController.getTrueHeading();

                // Rotate the movement direction counter to the bot's rotation
                double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
                double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

                rotX = rotX * 1.1;  // Counteract imperfect strafing

                // Denominator is the largest motor power (absolute value) or 1
                // This ensures all the powers maintain the same ratio,
                // but only if at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
                double frontLeftPower = (rotY + rotX + rx) / denominator;
                double backLeftPower = (rotY - rotX + rx) / denominator;
                double frontRightPower = (rotY - rotX - rx) / denominator;
                double backRightPower = (rotY + rotX - rx) / denominator;

                leftFront.setPower(frontLeftPower*speedConstant);
                leftBack.setPower(backLeftPower*speedConstant);
                rightFront.setPower(frontRightPower*speedConstant);
                rightBack.setPower(backRightPower*speedConstant);
            } else if (fieldOriented == false) {
                double y = -Ay; // Remember, Y stick value is reversed
                double x = Ax * 1.1; // Counteract imperfect strafing
                double rx = Aturn;

                // Denominator is the largest motor power (absolute value) or 1
                // This ensures all the powers maintain the same ratio,
                // but only if at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                double frontLeftPower = (y + x + rx) / denominator;
                double backLeftPower = (y - x + rx) / denominator;
                double frontRightPower = (y - x - rx) / denominator;
                double backRightPower = (y + x - rx) / denominator;

                leftFront.setPower(frontLeftPower*speedConstant);
                leftBack.setPower(backLeftPower*speedConstant);
                rightFront.setPower(frontRightPower*speedConstant);
                rightBack.setPower(backRightPower*speedConstant);
            }
        } else {
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);
        }
    }


    public static void resetMecanumEncoder() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public static void powerForward(double speed) {
        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);
    }

    public static void powerStop() {
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }

    public static boolean moveForward(double cm, double speed) {
        double circumfrence = (7.5)*(Math.PI);
        double rotations = rightFront.getCurrentPosition()/encoderTicks;
        double distanceTravelled = circumfrence * rotations;

        if (cm - distanceTravelled >= 0) {
            powerForward(speed);
            return (true);
        } else {
            powerStop();
            resetMecanumEncoder();
            return (false);
        }
    }

    public static boolean rotate(double degrees, double currentOrientation, double speed) { //degrees in TrueNorth
        if (Math.abs(currentOrientation) < Math.abs(degrees)) {
            if (degrees < 0) {
                leftFront.setPower(-speed);
                rightFront.setPower(speed);
                leftBack.setPower(-speed);
                rightBack.setPower(speed);
            } else {
                leftFront.setPower(speed);
                rightFront.setPower(-speed);
                leftBack.setPower(speed);
                rightBack.setPower(-speed);
            }
            return true;
        } else {
            return false;
        }
    }

    public static String getDebug() {
        return "LB: " + leftBack.getCurrentPosition() + " , RB: " + rightBack.getCurrentPosition() + " , LF: " + leftFront.getCurrentPosition() + " , RF: " + rightFront.getCurrentPosition();
    }
}
