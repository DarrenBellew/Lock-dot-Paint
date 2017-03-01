package com.islarf6546.gmail.lock_prototype_1;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        JSON_Helper jsonObj = new JSON_Helper("password.json");
        JSONObject simplevalue = new JSONObject();
        try {
            //simplevalue.put("password", "helloWorld");
            simplevalue = jsonObj.loadJSON(this);
            AndroidHelper.makeToast(this, simplevalue.toString(), false);
            System.out.println(simplevalue);
            jsonObj.writeJSON(simplevalue, this);


        }
        catch(JSONException jsone)  {
            AndroidHelper.makeToast(this, "JSON Exception", false);
            jsone.printStackTrace();
        }



        //Gets display (screen) size to calculate an area of freedom
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels/10;
        final int width = displayMetrics.widthPixels/10;
        final int freedom = (int) (GeometryMath.round((double) ((height < width) ? height : width), 10) * 1.5);



        final DrawView d = (DrawView) findViewById(R.id.drawArea);


        Button sub = (Button) findViewById(R.id.submit_button);
        sub.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("Freedom: " + freedom);

                        d.displayStrokes(freedom, getBaseContext());
                    }
                }
        );

        Button create = (Button) findViewById(R.id.create_button);
        create.setOnClickListener(
                new View.OnClickListener()  {
                    @Override
                    public void onClick(View view)  {
                        d.createPassword(getBaseContext());
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        //getMenuInflater().inflate(R.menu.activity_write_on_screen, menu);
        return true;
    }


}