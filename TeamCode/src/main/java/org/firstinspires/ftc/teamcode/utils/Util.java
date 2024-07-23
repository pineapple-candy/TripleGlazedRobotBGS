package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;

public class Util {
    public static double toRad(double degrees) {
        return Math.toRadians(degrees);
    }

    public static double toDeg(double radians) {
        return Math.toDegrees(radians);
    }

    public static double toCM(double inches) {
        return inches * 2.54;
    }

    public static double toIn(double cm) {
        return cm / 2.54;
    }

}
