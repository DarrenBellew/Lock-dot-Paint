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

    Canvas storeCanv;

    //SETUP CODE
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
    //END SETUP CODE



    //onDraw. clear code does not work yet.
    @Override
    protected void onDraw(Canvas canvas)  {
        canvas.drawPath(path, paint);
    }


    //Executes on touching
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

    public boolean displayStrokes(int freedom, Context ctx2)  {
        strokes = GeometryMath.translateShapeToOrigin(strokes);
        return PasswordHelper.comparePw(strokes, freedom, ctx2);
    }

    public void createPassword(Context ctx2)  {
        strokes = GeometryMath.translateShapeToOrigin(strokes);
        PasswordHelper.storeNew(strokes,ctx2);
    }

    public void clearCanvas()  {
        path.reset();
        System.out.println(strokes);
        strokes = new ArrayList<>();

        invalidate();
    }

}
