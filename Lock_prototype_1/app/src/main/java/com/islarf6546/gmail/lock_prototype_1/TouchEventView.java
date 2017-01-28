package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;


/**
 * Created by Islarf on 06/12/2016.
 */
/* ***** NOTE *******
REFERENCE FOR TOUCHEVENTVIEW CODE SNIPPET:: https://www.youtube.com/watch?v=fRtytvw3yvY&t=555s
*/


public class TouchEventView extends LinearLayout {
    private Paint paint = new Paint();
    private Path path = new Path();

    Coordinate delim = new Coordinate(-1,-1);

    ArrayList<Stroke> strokes = new ArrayList<Stroke>();
    Stroke stroke;

    private boolean finger_down = false;

    public TouchEventView(Context ctx, AttributeSet attrs)  {

        super(ctx, attrs);


        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

        Button b = new Button(ctx);
        b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        b.setText("Display coordinates");
        b.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v)  {
                displayCoordinates();
            }
        });

        super.addView(b);



    }

    @Override
    protected void onDraw(Canvas canvas)  {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)  {
        // new line delimited by -1,-1, since the user should not be able to draw outside of positive
        // space.

        stroke = new Stroke();

        Coordinate start;
        Coordinate end;
        Coordinate temp;

        temp = new Coordinate(event.getX(), event.getY());

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stroke.addCoordinate(temp);
                finger_down = true;
                path.moveTo(temp.getX(), temp.getY());
                return true;

            case MotionEvent.ACTION_MOVE:
                stroke.addCoordinate(temp);
                finger_down = true;
                path.lineTo(temp.getX(), temp.getY());
                //System.out.println("X: " + xPos + " Y: " + yPos);
                break;
            case MotionEvent.ACTION_UP:
                strokes.add(stroke);
                break; //do nothing, finger up

            default:
                return false;
        }


        //schedule a repaint
        invalidate();
        return true;
    }


    public void displayCoordinates()  {
        for(int i=0; i < strokes.size(); i++)  {
            System.out.println((i+1) + ": " + strokes.get(i).toString());
        }
    }

    private int roundUp(float n, int x)  {
        return (int) Math.ceil((n / x) * x);
    }
}


