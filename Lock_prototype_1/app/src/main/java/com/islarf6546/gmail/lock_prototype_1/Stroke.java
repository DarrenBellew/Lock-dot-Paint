package com.islarf6546.gmail.lock_prototype_1;


/*
* Author: Darren Bellew
*
* This class holds two coordinates, which are the starting and ending to a stroke.
* It is used by the DrawView class.
*/

public class Stroke {



    private Coordinate start;
    private Coordinate end;

    public Stroke(Coordinate start, Coordinate end){
        this.start=start;
        this.end=end;
    }

    public Stroke(Coordinate start)  {
        this(start, null);
    }



    public void setStart(Coordinate start)  {
        this.start=start;
    }

    public Coordinate getStart()  {
        return start;
    }

    public void setEnd(Coordinate end)  {
        this.end=end;
    }

    public Coordinate getEnd()  { return end; }


    @Override
    public String toString()  {
        return "Start: " + getStart() + " || End: " + getEnd();
    }
}
