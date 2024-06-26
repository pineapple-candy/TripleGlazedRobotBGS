package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class ControllerIMU {
    private static IMU imu = null;

    public static double adjustedYaw;

    private static double offset;

    public static void initIMU(IMU imuArg, double givenOffset) { //< --  hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu = imuArg;
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        offset = givenOffset;
    }

    public static double getTrueHeading() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }

    public static void resetIMU() {
        imu.resetYaw();
    }

    public static double getAdjustedHeading() {
        return getTrueHeading() + offset; // 90 degrees
    }

}
