package com.islarf6546.gmail.lock_prototype_1;

/**
 * Created by Islarf on 22/01/2017.
 */

public class Coordinate {

    int x;
    int y;

    public Coordinate(int x, int y)  {
        this.x=(int) GeometryMath.round(x, 100);
        this.y=(int) GeometryMath.round(y, 100);
    }

    public int getX()  {
        return x;
    }
    public int getY()  {
        return y;
    }

    public void setCoords(int x, int y)  {
        this.x = x;
        this.y = y;
    }

    public String toString()  {
        return "("+getX()+","+getY()+")";
    }
}
