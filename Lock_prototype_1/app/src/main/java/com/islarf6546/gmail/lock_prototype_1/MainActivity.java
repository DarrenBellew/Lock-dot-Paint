package com.islarf6546.gmail.lock_prototype_1;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(new TouchEventView(this,null));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        //getMenuInflater().inflate(R.menu.activity_write_on_screen, menu);
        return true;
    }

}