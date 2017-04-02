package com.islarf6546.gmail.lock_prototype_1;

import java.util.ArrayList;

/**
 * Created by Islarf on 23/01/2017.
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
