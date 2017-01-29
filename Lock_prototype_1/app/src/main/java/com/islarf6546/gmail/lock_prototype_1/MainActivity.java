package com.islarf6546.gmail.lock_prototype_1;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        final DrawView d = (DrawView) findViewById(R.id.drawArea);


        Button sub = (Button) findViewById(R.id.submit_button);
        sub.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.displayStrokes();
                    }
                }
        );

        Button clr = (Button) findViewById(R.id.clear_button);
        clr.setOnClickListener(
                new View.OnClickListener()  {
                    @Override
                    public void onClick(View view)  {
                        d.clear();
                    }
                }
        );

        //setContentView(R.layout.activity_main);

        //getWindow().addContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        //getMenuInflater().inflate(R.menu.activity_write_on_screen, menu);
        return true;
    }


}