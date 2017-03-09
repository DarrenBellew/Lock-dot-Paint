package com.islarf6546.gmail.lock_prototype_1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainDrawActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent i = getIntent();



        //TESTING
        JSON_Helper jsonObj = new JSON_Helper("password.json");
        JSONObject simplevalue = null;
        try {
            //simplevalue.put("password", "helloWorld");
            simplevalue =  jsonObj.loadJSON(this);
            AndroidHelper.makeToast(MainDrawActivity.super.getApplicationContext(),simplevalue.toString(),false);
            System.out.println(simplevalue);
            jsonObj.writeJSON(simplevalue, this);


        }
        catch(JSONException jsone)  {
            jsone.printStackTrace();
        }
        //END TESTING


        //Gets display (screen) size to calculate an area of freedom
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels/10;
        final int width = displayMetrics.widthPixels/10;
        final int freedom = (int) (GeometryMath.round((double) ((height < width) ? height : width), 10) * 1.5);

        //initialise draw area
        final DrawView d = (DrawView) findViewById(R.id.drawArea);

        //initialise buttons
        final Button sub = (Button) findViewById(R.id.submit_button);
        final Button create = (Button) findViewById(R.id.create_button);

        if(i.getBooleanExtra(getString(R.string.change_pw), false)) {
            create.setVisibility(View.VISIBLE);
            AndroidHelper.makeToast(this, "Create new password", false);
            create.setOnClickListener(
                    new View.OnClickListener()  {
                        @Override
                        public void onClick(View view) {
                            d.createPassword(MainDrawActivity.this);
                            AndroidHelper.makeToast(MainDrawActivity.this, "Password created", false);
                            killActivity();
                        }
                    }
            );
        }
        else if (i.getBooleanExtra(getString(R.string.change_pw_check), false)) {
            sub.setVisibility(View.VISIBLE);
            AndroidHelper.makeToast(this, "Enter current password", false);
            sub.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent returnIntent = new Intent();
                            boolean isCorrect = d.comparePw(freedom, MainDrawActivity.this);
                            if(!isCorrect)  {
                                AndroidHelper.makeToast(MainDrawActivity.this, "Incorrect, try again", false);
                            }
                            else {
                                AndroidHelper.makeToast(MainDrawActivity.this, "Correct", false);
                                returnIntent.putExtra(getString(R.string.change_pw_check), true);
                                setResult(Activity.RESULT_OK, i);
                                killActivity();
                            }
                        }
                    }
            );

        }
        else  {
            sub.setVisibility(View.VISIBLE);
            sub.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(d.comparePw(freedom, MainDrawActivity.this))  {
                                AndroidHelper.makeToast(MainDrawActivity.this,"CORRECT", false);
                                //killActivity();
                            }
                            else  {
                                AndroidHelper.makeToast(MainDrawActivity.this,"try again",false);
                            }
                        }
                    }
            );

        }

        Button clear = (Button) findViewById(R.id.clear_button);
        clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.clearCanvas();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_write_on_screen, menu);
        return true;
    }

    private void killActivity()  {
        finish();
    }

}