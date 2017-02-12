package com.islarf6546.gmail.lock_prototype_1;

/**
 * Created by islarf on 06/02/17.
 */

public class Line {

    Coordinate start;
    Coordinate end;
    float length;

    public Line(Coordinate start, Coordinate end)  {
        this.start=start;
        this.end=end;

        this.length = MyMath.getLength(this);
    }


    public float getLength()  {
        return length;
    }

    public Coordinate[] getCoordinates()  {
        Coordinate[] toRet = new Coordinate[2];
        toRet[0] = start;
        toRet[1] = end;
        return toRet;
    }

    @Override
    public String toString()  {
        return "(X1, Y1): ("+start.getX()+", "+start.getY()+"), (X2, Y2): "+end.getX()+", "+end.getY()+")";
    }


}
