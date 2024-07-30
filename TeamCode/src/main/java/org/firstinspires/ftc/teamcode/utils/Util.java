package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;

import java.util.ArrayList;
import java.util.List;

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

    public static double maxList(ArrayList<Double> list) {
        double currentMax = list.get(0);

        for(int i = 1; i < list.size(); i++) {
            currentMax = Math.max(currentMax,list.get(i));
        }

        return currentMax;
    }

}
