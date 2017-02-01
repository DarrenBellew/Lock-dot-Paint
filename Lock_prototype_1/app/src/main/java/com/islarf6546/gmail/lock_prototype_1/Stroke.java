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
        //if(first != null)  {
        //    first = c;
        //}
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


    public ArrayList<Coordinate> getAList()  {

        return coordinates;

        /*String toretr = "{";

        for(Coordinate coordIter : coordinates)  {
            toretr += "X: " + coordIter.getX() + ", Y: " + coordIter.getY();
        }

        toretr+="}";
        return toretr;*/
    }

}
