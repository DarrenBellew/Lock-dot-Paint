package com.islarf6546.gmail.lock_screen_settings;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button b;
    MainActivity maContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b = (Button) findViewById(R.id.password_button);
        b.setOnClickListener(
                new View.OnClickListener()  {
                    public void onClick(View v)  {
                        Intent toPWChange = new Intent(maContext, Enter_Pw.class);
                        startActivity(toPWChange);
                    }
                }
        );





    }



}
