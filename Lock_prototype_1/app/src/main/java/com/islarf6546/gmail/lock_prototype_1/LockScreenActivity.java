package com.islarf6546.gmail.lock_prototype_1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class LockScreenActivity extends Activity {

    int attempts;
    String actionBarTitle;
    private static boolean running = false;


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


        //PasswordHelper.displayPw(this);

        resetAttempts();

        actionBarTitle = i.getStringExtra(getString(R.string.actionBar));
        changeTitle(actionBarTitle);


        if(i.getBooleanExtra(getString(R.string.change_pw), false)) {
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
            if(!i.getBooleanExtra(this.getString(R.string.testTitle), false))  {
                actionBarTitle = getString(R.string.attemptTitle);
                getActionBar().setTitle(actionBarTitle + attempts);
            }
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
                                incorrectAttempt();
                                getActionBar().setTitle(actionBarTitle + attempts);
                                AndroidHelper.makeToast(LockScreenActivity.this,"try again",false);
                            }
                        }
                    }
            );

        }

        System.out.println("I made it here");

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

    private void incorrectAttempt()  {
        attempts -= 1;
    }

    private void resetAttempts()  {
        attempts = Integer.parseInt(getString(R.string.attempts));
    }

    private void changeTitle(String title)  {
        getActionBar().setTitle(title);
    }

}