package com.islarf6546.gmail.lock_prototype_1;

/**
 * Created by Islarf on 22/01/2017.
 */

public class Coordinate {

    float x;
    float y;

    public Coordinate(float x, float y)  {
        this.x=x;
        this.y=y;
    }

    public float getX()  {
        return x;
    }
    public float getY()  {
        return y;
    }

    public void setCoords(float x, float y)  {
        this.x = x;
        this.y = y;
    }

    public String toString()  {
        return "||X: " + x + " || Y: " + y + "||";
    }
}
