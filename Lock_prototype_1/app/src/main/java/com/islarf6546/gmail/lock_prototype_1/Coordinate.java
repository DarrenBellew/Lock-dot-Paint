package com.islarf6546.gmail.lock_prototype_1;

/*
* Author: Darren Bellew
*
* This class is for Coordinates. holds 2 integers, an x and a y value.
* Also has a funtion to round the coordinates to the neares 100.
 */

public class Coordinate {

    int x;
    int y;

    public Coordinate(int x, int y)  {
        if(x < 0)  {
            x = 0;
        }
        if(y < 0)  {
            y = 0;
        }

        this.x=x;
        this.y=y;
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

    public void roundCoords()  {
        x = (int) GeometryMath.round(x,100);
        y = (int) GeometryMath.round(y,100);
    }

    public String toString()  {
        return "("+getX()+","+getY()+")";
    }
}
