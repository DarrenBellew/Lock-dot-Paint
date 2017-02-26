package com.islarf6546.gmail.lock_prototype_1;

/**
 * Created by Islarf on 22/01/2017.
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
