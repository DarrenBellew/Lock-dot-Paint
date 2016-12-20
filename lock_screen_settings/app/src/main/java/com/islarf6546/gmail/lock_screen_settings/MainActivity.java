package com.islarf6546.gmail.lock_screen_settings;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    String[] listOptions = new String[]  {
            "New Password",
            "Activate Lock(dot)Paint",
            "Change accuracy",
            "Canvas Background"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter options_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOptions);
        ListView options_ListView = (ListView) findViewById(R.id.options_list);
        options_ListView.setAdapter(options_adapter);

        options_ListView.setOnItemClickListener(

                new AdapterView.OnItemClickListener()  {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String option = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, option, Toast.LENGTH_SHORT).show();
                    }
                }
        );



    }
}
