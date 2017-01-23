package com.islarf6546.gmail.lock_prototype_1;

import java.util.ArrayList;

/**
 * Created by Islarf on 23/01/2017.
 */

public class Stroke {

    ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    Coordinate first = null;


    public Stroke(){}

    public void addCoordinate(Coordinate c)  {
        if(first != null)  {
            first = c;
        }
        coordinates.add(c);
    }

    public ArrayList<Coordinate> getCoordinates()  {
        return coordinates;
    }

    public Coordinate getFirst()  {
        return first;
    }
    public Coordinate getLast()  {
        return coordinates.get(coordinates.size()-1);
    }

    public String toString()  {
        String toretr = "{";

        Coordinate temp;

        for(int i=0; i < coordinates.size(); i++)  {
            temp = coordinates.get(i);
            toretr += "X: " + temp.getX() + ", Y: " + temp.getY();

            if(i != coordinates.size()-2)  {
                toretr+=", ";
            }
        }

        toretr+="}";
        return toretr;
    }

}
