package com.islarf6546.gmail.lock_prototype_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AppSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        String[] settings = {
                "New Password", "enable/disable lockscreen"
        };

        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                settings);

        ListView settingsList = (ListView) findViewById(R.id.settingsListView);

        settingsList.setAdapter(theAdapter);

        settingsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String settingPicked = "Selected: " +
                            String.valueOf(adapterView.getItemAtPosition(i));
                        AndroidHelper.makeToast(AppSettingsActivity.this, settingPicked, false);

                    }
                });
    }
}
