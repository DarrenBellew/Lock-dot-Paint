package com.islarf6546.gmail.lock_prototype_1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/*
* Author: Darren Bellew
*
* This class is the activity that acts as the lock screen for the device.
* The class has ifs that check the intent that called it, to do slightly different things, depending.
*/

public class LockScreenActivity extends Activity {

    String actionBarTitle;
    private static boolean running = false;

    /*
    * These next two functions run on start and on stop of the activity. they set a static boolean to be checked by outside classes.
    */
    @Override
    public void onStart()  {
        running = true;
        super.onStart();
    }
    @Override
    public void onStop()  {
        running = false;
        super.onStop();
    }

    public static boolean isRunning()  {
        return running;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.lock_screen_activity);

        //This intent is used to check get the action bar title, and what functionality the sub/create button will be doing.
        final Intent i = getIntent();

        //Gets display (screen) size to calculate an area of freedom
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels/10;
        final int width = displayMetrics.widthPixels/10;
        final int freedom = (int) (GeometryMath.round((double) ((height < width) ? height : width), 10) * 2);

        //initialise draw area
        final DrawView d = (DrawView) findViewById(R.id.drawArea);

        //initialise buttons
        final Button sub = (Button) findViewById(R.id.submit_button);
        final Button create = (Button) findViewById(R.id.create_button);

        actionBarTitle = i.getStringExtra(getString(R.string.actionBar));
        changeTitle(actionBarTitle);

        /*
        * Collection of if statements to decide which to use and what to do with the submit or create button.
         */
        if(i.getBooleanExtra(getString(R.string.change_pw), false)) {
            /*
            * If the intent is set to change password.
            */
            create.setVisibility(View.VISIBLE);

            create.setOnClickListener(
                    new View.OnClickListener()  {
                        @Override
                        public void onClick(View view) {
                            d.createPassword(LockScreenActivity.this);
                            AndroidHelper.makeToast(LockScreenActivity.this, "Password created", false);
                            killActivity();
                        }
                    }
            );
        }
        else if (i.getBooleanExtra(getString(R.string.checkPw), false)) {
            /*
            * If the intent is set to check password.
            */
            sub.setVisibility(View.VISIBLE);

            sub.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent returnIntent = new Intent();
                            boolean isCorrect = d.comparePassword(freedom, LockScreenActivity.this);
                            if(!isCorrect)  {
                                AndroidHelper.makeToast(LockScreenActivity.this, "Incorrect, try again", false);
                            }
                            else {
                                AndroidHelper.makeToast(LockScreenActivity.this, "Correct", false);
                                returnIntent.putExtra(getString(R.string.checkPw_confirm), true);
                                setResult(Activity.RESULT_OK, returnIntent);
                                killActivity();
                            }
                        }
                    }
            );

        }
        else  {
            /*
            * If else, it simply just asked for the user to enter their password and kills the activity when the user is correct.
            */
            changeTitle(getString(R.string.enter_pw));
            sub.setVisibility(View.VISIBLE);
            sub.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(d.comparePassword(freedom, LockScreenActivity.this))  {
                                AndroidHelper.makeToast(LockScreenActivity.this,"CORRECT", false);
                                killActivity();
                            }
                            else  {
                                AndroidHelper.makeToast(LockScreenActivity.this,"try again",false);
                            }
                        }
                    }
            );

        }

        /*
        * Button to clear the Canvas
        */
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
        return true;
    }

    private void killActivity()  {
        finish();
    }


    private void changeTitle(String title)  {
        getActionBar().setTitle(title);
    }

}