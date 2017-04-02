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

/**
 * Created by YamiVegeta on 25/01/2017.
 *
 * A lot of the code on this is abstracted and changed from that in this youtube video: https://www.youtube.com/watch?v=fRtytvw3yvY
 *
 */
public class DrawView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();


    ArrayList<Stroke> strokes = new ArrayList<>();
    Stroke curStroke = null;
    Context ctx;


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
