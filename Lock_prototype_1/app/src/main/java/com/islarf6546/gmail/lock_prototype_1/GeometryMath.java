package com.islarf6546.gmail.lock_prototype_1;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/*
* Author: Darren Bellew
*
* This class provides static functions that will execute geometry related mathematics and return a result.
*/

public class GeometryMath {


    public static long round(double n, int v)  {
        return Math.round(n/v) * v;
    }

    public static boolean isCorrect(ArrayList<Stroke> storePw, ArrayList<Stroke> input, int freedom)  {
        /*
        * This method checks if the first array list, "is correct" to the inputted array list.
        * Freedom is used as an area of freedom to allow for human error.
        */
        if(storePw.size() == input.size()) {

            for(int i=0; i < storePw.size(); i++)  {
                if(totalDistance(storePw.get(i).getStart(), input.get(i).getStart()) > freedom)  {
                    return false;
                }
                else if(totalDistance(storePw.get(i).getEnd(), input.get(i).getEnd()) > freedom)  {
                    return false;
                }
            }
        }
        else  {
            return false;
        }

        return true;
    }

    public static double totalDistance(Coordinate x, Coordinate y)  {
        /*
        * Gets the distance between two coordinates and returns the result as a double.
        */
        double distance = Math.sqrt(
                Math.pow(y.getX() - x.getX(), 2)
                + Math.pow(y.getY() - x.getY(), 2));

        return distance;
    }

    public static ArrayList<Stroke> translateShapeToOrigin(ArrayList<Stroke> toTranslate)  {

        /*
        * This function gets the smallest x and y value over the entire array list, and subtracts them from the x and y values, respectively, of all the start and end points.
        */

        ArrayList<Stroke> translatedShape = new ArrayList<>();

        Coordinate smallest = findSmallest(toTranslate);

        for(Stroke s : toTranslate)  {

            s.setStart(new Coordinate(
                    s.getStart().getX() - smallest.getX(),
                    s.getStart().getY() - smallest.getY()
            ));
            s.setEnd(new Coordinate(
                    s.getEnd().getX() - smallest.getX(),
                    s.getEnd().getY() - smallest.getY()
            ));

            translatedShape.add(s);
        }

        return translatedShape;
    }

    public static Coordinate findSmallest(ArrayList<Stroke> toTranslate)  {
        /*
        * Finds the smallest x and y values in a list of strokes.
         */
        Coordinate toRet;
        int y = Integer.MAX_VALUE;
        int x = Integer.MAX_VALUE;

        for(Stroke str : toTranslate)  {
            if(str.getStart().getX() < x)  {
                x = str.getStart().getX();
            }
            if(str.getEnd().getX() < x)  {
                x = str.getEnd().getX();
            }

            if(str.getStart().getY() < y)  {
                y = str.getStart().getY();
            }
            if(str.getEnd().getY() < y)  {
                y = str.getEnd().getY();
            }
        }
        toRet = new Coordinate(x,y);
        return toRet;
    }
}
