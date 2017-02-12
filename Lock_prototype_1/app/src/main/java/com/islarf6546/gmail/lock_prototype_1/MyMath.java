package com.islarf6546.gmail.lock_prototype_1;

/**
 * Created by islarf on 08/02/17.
 */

public class MyMath {


    public static float angleOfTwo(Line a, Line b)  {
        float angleA;
        float angleB;
        float degPi = 180;


        angleA = (float) Math.toDegrees(Math.atan(getSlope(a)));
        angleB = (float) Math.toDegrees(Math.atan(getSlope(b)));

        return ( degPi - Math.abs((angleA - angleB)));
    }

    public static float getLength(Line l)  {
        Coordinate[] coords = l.getCoordinates();
        Coordinate a = coords[0];
        Coordinate b = coords[1];


        return (float) Math.sqrt(Math.pow((b.getX() - a.getX()),2) + Math.pow((b.getY() - a.getY()),2));
    }


    public static float getSlope(Coordinate a, Coordinate b)  {
        return((b.getY() - a.getY())
                / (b.getX() - a.getX()));
    }

    public static float getSlope(Line l)  {
        return getSlope(l.getCoordinates()[0], l.getCoordinates()[1]);
    }

    public static float yIntercept(Coordinate a, Coordinate b)  {
        float m = getSlope(a,b);
        return (-(m*a.getX()) + a.getY());
    }

    public static double round(double val, int scale)  {
        int rounder = 10 * scale;
        return Math.round(val / rounder) * rounder;
    }

    public static double round(float val, int scale)  {
        return round((double) val, scale);
    }
}
