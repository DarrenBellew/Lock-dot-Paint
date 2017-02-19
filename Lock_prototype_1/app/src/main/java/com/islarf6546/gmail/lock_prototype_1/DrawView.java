package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
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

    ArrayList<Stroke> pwStrokes = new ArrayList<>();


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
        /*WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;*/


        /*&if(GeometryMath.isCorrect(pwStrokes, strokes))  {
            Toast.makeText(ctx,"Success",
                Toast.LENGTH_LONG).show();
        }
        else  {
            Toast.makeText(ctx, "Failure",
                    Toast.LENGTH_LONG).show();
        }*/

        System.out.println("BEFORE TRANSLATE: ");
        for(Stroke i : strokes)  {
            System.out.println(i.toString()+"\n");
        }
        strokes = GeometryMath.translateShapeToOrigin(strokes);
        System.out.println("AFTER TRANSLATE: ");
        for(Stroke i : strokes)  {
            System.out.println(i.toString()+"\n");
        }




        /*
        //writeToJson
        JSON_Helper jHelper = new JSON_Helper("password.json", getContext());
        try {
            JSONObject json_data = jHelper.loadJSON(ctx);


            System.out.println(json_data.toString(4));


        }
        catch(JSONException je)  {
            je.printStackTrace();
        }

        Coordinate start = strokes.get(0).getStart();
        Coordinate end = strokes.get(0).getEnd();



        JSONArray coordsArr = new JSONArray();
        JSONObject coordsObj = new JSONObject();

        JSONObject coords_subObj = new JSONObject();

        /*try  {

            coordsObj.put("x", start.getX());
            coordsObj.put("y", start.getY());

            coordsArr.put(coordsObj);

            coordsObj.put("x", end.getX());
            coordsObj.put("y", end.getY());

            coordsArr.put(coordsObj);
            json_data.put("password", coordsArr);
            json_data.put("numPoints", coordsArr.length());

            jHelper.setJson_data(json_data);
            if(jHelper.writeJSONToAsset(getContext()))  {
                System.out.println("Write success");
            }
            else  {
                System.out.println("Write failure");
            }


        }
        catch(JSONException e)  {
            e.printStackTrace();
        }


        */


    }

}
