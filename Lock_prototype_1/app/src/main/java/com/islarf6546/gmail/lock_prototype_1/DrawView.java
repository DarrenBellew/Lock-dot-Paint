package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import static android.R.color.white;

/**
 * Created by YamiVegeta on 25/01/2017.
 */
public class DrawView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    ArrayList<Stroke> strokes = new ArrayList<Stroke>();
    Stroke curStroke = null;


    boolean clearCanvas = false;

    private boolean finger_down = false;


    public DrawView(Context context) {
        super(context);
        init(null,0, context);

    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0, context);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, context);
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
        /*if(clearCanvas == true)  {
            canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
            path.reset();
            clearCanvas = false;

            this.invalidate();
        }*/
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)  {

        Coordinate temp = new Coordinate((int) event.getX(), (int) event.getY());

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                curStroke = new Stroke(temp);
                finger_down = true;
                path.moveTo(temp.getX(), temp.getY());
                return true;

            case MotionEvent.ACTION_MOVE:
                finger_down = true;
                path.lineTo(temp.getX(), temp.getY());
                break;
            case MotionEvent.ACTION_UP:
                curStroke.setEnd(temp);
                strokes.add(curStroke);
                curStroke = null;

                finger_down = false;
                break;

            default:
                return false;
        }



        //schedule a repaint
        invalidate();
        return true;
    }

    public void displayStrokes()  {
        for(Stroke i : strokes)  {
            System.out.println(i.toString()+"\n");
        }
    }

}
