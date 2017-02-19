package com.islarf6546.gmail.lock_prototype_1;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by islarf on 18/02/17.
 */

public class GeometryMath {


    public static void CompareAccuracy(Coordinate source, Coordinate input)  {

    }

    public static void TranslateToZero(ArrayList<Stroke> str)  {

    }

    public static int closestToOrigin(ArrayList<Stroke> str, boolean axis)  {
        //axis: true = x, false = y.
        int toRet = 0;



        return toRet;
    }

    public static long round(double n, int v)  {
        return Math.round(n/v) * v;
    }

    public static boolean isCorrect(ArrayList<Stroke> storePw, ArrayList<Stroke> input)  {


        if(storePw.size() == input.size()) {

            for(int i=0; i < storePw.size(); i++)  {
                if(totalDistance(storePw.get(i).getStart(), input.get(i).getStart()) > 100)  {
                    return false;
                }
                else if(totalDistance(storePw.get(i).getEnd(), input.get(i).getEnd()) > 100)  {
                    return false;
                }
            }
        }

        return true;
    }

    public static double totalDistance(Coordinate store, Coordinate input)  {
        double distance = Math.sqrt(
                Math.pow(input.getX() - store.getX(), 2)
                + Math.pow(input.getY() - store.getY(), 2));

        return distance;
    }

    public static ArrayList<Stroke> translateShapeToOrigin(ArrayList<Stroke> toTranslate)  {
        ArrayList<Stroke> translatedShape = new ArrayList<>();

        Coordinate smallest = findSmallest(toTranslate);

        for(Stroke s : toTranslate)  {

            s.setStart(new Coordinate(s.getStart().getX() - smallest.getX(),
                    s.getStart().getY() - smallest.getY()));
            s.setEnd(new Coordinate(
                    s.getEnd().getX() - smallest.getX(),
                    s.getEnd().getY() - smallest.getY()
            ));

            translatedShape.add(s);
        }

        return translatedShape;
    }

    public static Coordinate findSmallest(ArrayList<Stroke> toTranslate)  {
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
                y = str.getStart().getY();
            }
        }
        toRet = new Coordinate(x,y);
        return toRet;
    }
}
