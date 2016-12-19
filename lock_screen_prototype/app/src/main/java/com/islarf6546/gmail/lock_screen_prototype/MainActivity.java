package com.islarf6546.gmail.lock_screen_prototype;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private static boolean active = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        active=true;

        if(!Lock_Disable.isActive()) {
            startService(new Intent(this, Lock_Disable.class));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                active = false;
            }
        });
    }

    public static boolean isActive()  {
        return active;
    }


}
