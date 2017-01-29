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

    Coordinate delim = new Coordinate(-1,-1);

    ArrayList<Stroke> strokes = new ArrayList<Stroke>();

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

        Stroke stroke = new Stroke();

        Coordinate temp;

        temp = new Coordinate(event.getX(), event.getY());

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stroke.addCoordinate(temp);
                //finger_down = true;
                path.moveTo(temp.getX(), temp.getY());
                return true;

            case MotionEvent.ACTION_MOVE:
                stroke.addCoordinate(temp);
                finger_down = true;
                path.lineTo(temp.getX(), temp.getY());
                break;
            case MotionEvent.ACTION_UP:
                stroke.addCoordinate(temp);
                strokes.add(stroke);
                break; //do nothing, finger up

            default:
                return false;
        }


        //schedule a repaint
        invalidate();
        return true;
    }

    public void displayStrokes()  {
        System.out.println("Stroke size: " + strokes.size());





        for(Stroke strokeIter : strokes)  {
            System.out.println(strokeIter);
        }
    }

    public void clear()  {
        strokes = new ArrayList<Stroke>();
        clearCanvas = true;
    }
}
