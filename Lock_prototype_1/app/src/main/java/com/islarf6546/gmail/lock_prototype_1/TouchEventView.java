package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Islarf on 06/12/2016.
 */
/* ***** NOTE *******
REFERENCE FOR TOUCHEVENTVIEW CODE SNIPPET:: https://www.youtube.com/watch?v=fRtytvw3yvY&t=555s
*/


public class TouchEventView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();


    private boolean finger_down = false;

    public TouchEventView(Context ctx, AttributeSet attrs)  {
        super(ctx, attrs);

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

    @Override
    public boolean onTouchEvent(MotionEvent event)  {
        float xStart;
        float yStart;
        float xEnd;
        float yEnd;
        float xPos = event.getX();
        float yPos = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                finger_down = true;
                path.moveTo(xPos, yPos);
                return true;

            case MotionEvent.ACTION_MOVE:
                finger_down = true;
                path.lineTo(xPos, yPos);
                //System.out.println("X: " + xPos + " Y: " + yPos);
                break;
            case MotionEvent.ACTION_UP:
                break; //do nothing, finger up

            default:
                return false;
        }
        System.out.println("X: " + xPos + " Y: " + yPos);


        //schedule a repaint
        invalidate();
        return true;
    }
}

