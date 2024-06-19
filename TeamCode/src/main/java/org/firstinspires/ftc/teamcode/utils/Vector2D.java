package org.firstinspires.ftc.teamcode.utils;

import java.util.ArrayList;
import java.util.List;

public class Vector2D {

    // Magnitude and Direction values
    private double m;
    private double d;

    // Constructor
    public Vector2D(double valOne, double valTwo, boolean isCart) {
        if (isCart) {
            // If Cartesian is true, valOne is 'x' and valTwo is 'y'

            m = Math.sqrt((valOne*valOne) + (valTwo*valTwo));
            d = Util.toDeg(Math.atan2(valOne,valTwo));
        } else {
            m = valOne;
            d = valTwo;
        }
    }

    /*
    Vector2D add adds the current vector to the vector passed as an argument.
    Returns another vectors that is the sum of the two vectors.
     */
    public Vector2D add(Vector2D vec) {
        double newX = this.getX() + vec.getX();
        double newY = this.getY() + vec.getY();

        return new Vector2D(newX, newY, true);
    }

    public Vector2D sub(Vector2D vec) {
        double newX = this.getX() - vec.getX();
        double newY = this.getY() - vec.getY();
        return new Vector2D(newX, newY, true);
    }

    public Vector2D mul(Vector2D vec) {
        double newX = this.getX() * vec.getX();
        double newY = this.getY() * vec.getY();

        return new Vector2D(newX, newY, true);
    }

    public double[] getValues() {
        return new double[]{this.getX(), this.getY()};
    }


    public Vector2D rot(double angle) {
        return new Vector2D(m,(d+angle)%360,false);
    }

    public double getX() {
        return Math.cos(d)*m;
    }

    public double getY() {
        return Math.sin(d)*m;
    }

    public double getMag() {
        return m;
    }

    public double getDir() {
        return d;
    }

    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

}

