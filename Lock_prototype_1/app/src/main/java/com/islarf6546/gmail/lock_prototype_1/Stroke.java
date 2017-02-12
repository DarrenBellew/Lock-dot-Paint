package com.islarf6546.gmail.lock_prototype_1;

import java.util.ArrayList;

/**
 * Created by Islarf on 23/01/2017.
 */

public class Stroke {



    ArrayList<Line> lines = new ArrayList<Line>();

    Coordinate cur = null;



    public Stroke(){}

    public void addCoordinate(Coordinate c)  {
        if(cur != null)  {
            lines.add(new Line(cur, c));
        }
        cur = c;
    }


    public ArrayList<Line> getLines()  {
        return lines;
    }

    public float getAngle(int value)  {

        if(value >= 0 && value < lines.size()-1)  {
            return MyMath.angleOfTwo(lines.get(value), lines.get(value+1));
        }
        else  {
            return -2;
        }
    }

    public Coordinate getCur()  {
        return cur;
    }
    public Line getLast()  {
        return lines.get(lines.size()-1);
    }

    public ArrayList<Line> getAList()  {
        return lines;
    }



    @Override
    public String toString()  {
        String toRet = "";

        for (int i=0; i<lines.size(); i++)  {
            toRet += "\n"+lines.get(i).toString();
        }

        return toRet;
    }

    public String getLinePair(int value)  {
        return "a: " + lines.get(value).toString() + "\nb: " + lines.get(value+1).toString();

    }

}
