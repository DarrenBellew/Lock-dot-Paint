package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import static android.R.color.white;

/*
* Author: Darren Bellew
*
* This class is for Coordinates. holds 2 integers, an x and a y value.
* Also has a funtion to round the coordinates to the neares 100.
*
* The base functionality of this class, is abstracted and changed from that displayed in this youtube video: https://www.youtube.com/watch?v=fRtytvw3yvY
*
*/
public class DrawView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();


    ArrayList<Stroke> strokes = new ArrayList<>();
    Stroke curStroke = null;
    Context ctx;


    //SETUP CODE FOR CANVAS. SEE YOUTUBE VIDEO FOR REFERENCE.
    public DrawView(Context ctx) {
        super(ctx);
        init(null,0, ctx);
        this.ctx=ctx;
    }

    public DrawView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        init(attrs,0, ctx);
    }

    public DrawView(Context ctx, AttributeSet attrs, int defStyleAttr) {
        super(ctx, attrs, defStyleAttr);
        init(attrs, defStyleAttr, ctx);
    }

    public void init(AttributeSet attrs, int defStyle, Context ctx)  {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

    }

    @Override
    protected void onDraw(Canvas canvas)  {
        canvas.drawPath(path, paint);
    }

    //END SETUP CODE



    /*
    * This event will get called every time a touch event happens.
    * Action down, move and up are checked for using event.getAction(). on DOWN and UP the coordinates are stored as start and up respecively and stored into strokes on action_up.
    *
    * This class also moves the black stroke on the canvas.
    */
    @Override
    public boolean onTouchEvent(MotionEvent event)  {



        Coordinate temp = new Coordinate((int) event.getX(), (int) event.getY());

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(temp.getX(), temp.getY());
                temp.roundCoords();
                curStroke = new Stroke(temp);
                return true;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(temp.getX(), temp.getY());
                break;
            case MotionEvent.ACTION_UP:
                temp.roundCoords();
                curStroke.setEnd(temp);
                strokes.add(curStroke);
                curStroke = null;
                break;

            default:
                return false;
        }


        //schedule a repaint
        invalidate();
        return true;
    }


    /*
    * The following 2 functions are called to create the current password stored in stroke, or to compare the password and return a boolean.
    */
    public void createPassword(Context ctx2)  {
        strokes = GeometryMath.translateShapeToOrigin(strokes);
        PasswordHelper.storeNew(strokes,ctx2);
    }

    public boolean comparePassword(int freedom, Context ctx2)  {
        strokes = GeometryMath.translateShapeToOrigin(strokes);
        return PasswordHelper.comparePw(strokes, freedom, ctx2);
    }


    public void clearCanvas()  {
        path.reset();
        strokes = new ArrayList<>();
        invalidate();
    }

}
