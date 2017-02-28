package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.color.white;

/**
 * Created by YamiVegeta on 25/01/2017.
 */
public class DrawView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    ArrayList<Stroke> strokes = new ArrayList<>();
    Stroke curStroke = null;
    Context ctx;

    ArrayList<Stroke> pwStrokes = null;


    boolean clearCanvas = false;

    private boolean finger_down = false;


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

        if(pwStrokes == null)  {
            pwStrokes = new ArrayList<>();
            Coordinate p1 = new Coordinate(200,400);
            Coordinate p2 = new Coordinate(400,300);

            curStroke = new Stroke(p1, p2);
            pwStrokes.add(curStroke);
            pwStrokes = GeometryMath.translateShapeToOrigin(pwStrokes);
            curStroke = null;
        }


        Coordinate temp = new Coordinate((int) event.getX(), (int) event.getY());



        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(temp.getX(), temp.getY());
                temp.roundCoords();
                curStroke = new Stroke(temp);
                finger_down = true;
                return true;

            case MotionEvent.ACTION_MOVE:
                finger_down = true;
                path.lineTo(temp.getX(), temp.getY());
                break;
            case MotionEvent.ACTION_UP:
                temp.roundCoords();
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

    public void displayStrokes(int freedom)  {

        strokes = GeometryMath.translateShapeToOrigin(strokes);
        System.out.println("INPUT: ");
        for(Stroke i : strokes)  {
            System.out.println(i.toString()+"\n");
        }

        System.out.println("SOLUTION: ");
        for(Stroke i : pwStrokes)  {
            System.out.println(i.toString()+"\n");
        }

        boolean match = GeometryMath.isCorrect(strokes, pwStrokes, freedom);
        if(match)  {
            System.out.println("PHONE UNLOCKED");
        }
        else  {
            System.out.println("ACCESS DENIED");
        }

    }

    public void createPassword()  {

    }

}
