package com.islarf6546.gmail.lock_prototype_1;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import static com.islarf6546.gmail.lock_prototype_1.R.id.activity_main;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        //RelativeLayout thisview = new TouchEventView(this,null);

        setContentView(new TouchEventView(this,null));


        //setContentView(R.layout.activity_main);

        //getWindow().addContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        //getMenuInflater().inflate(R.menu.activity_write_on_screen, menu);
        return true;
    }

}