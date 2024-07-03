package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDrive {

    private static double speedConstant = 0.7;

    private static DcMotor leftBack;
    private static DcMotor leftFront;
    private static DcMotor rightFront;
    private static DcMotor rightBack;

    public static void initialiseMotors(DcMotor AleftBack, DcMotor AleftFront, DcMotor ArightFront, DcMotor ArightBack, ) {
        leftBack = AleftBack;
        leftFront = AleftFront;
        rightBack = ArightBack;
        rightFront = ArightFront;

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void runMotors(double y, double x, double turn) {
        if ((Math.abs(x) > 0.05) || (Math.abs(y) > 0.05) || (Math.abs(turn) > 0.05)) {

            double theta = Math.atan2(y, x) * 180/Math.PI; // aka angle

            double power = Math.hypot(x, y);

            double sin = Math.sin((theta * (Math.PI / 180)) - (Math.PI / 4)); // convert radians into degrees (superior measurement)
            double cos = Math.cos((theta * (Math.PI / 180)) - (Math.PI / 4));
            double maxSinCos = Math.max(Math.abs(sin), Math.abs(cos)); // getting max

            double leftFrontPower = (power * cos / maxSinCos + turn);
            double rightFrontPower = (power * sin / maxSinCos - turn);
            double leftBackPower = (power * sin / maxSinCos + turn);
            double rightBackPower = (power * cos / maxSinCos - turn);


            if ((power + Math.abs(turn)) > 1) {
                leftFrontPower /= power + turn;
                rightFrontPower /= power - turn;
                leftBackPower /= power + turn;
                rightBackPower /= power - turn;
            }

            leftFront.setPower(leftFrontPower);
            rightFront.setPower(rightFrontPower);
            leftBack.setPower(leftBackPower);
            rightBack.setPower(rightBackPower);
        }
    }
}
