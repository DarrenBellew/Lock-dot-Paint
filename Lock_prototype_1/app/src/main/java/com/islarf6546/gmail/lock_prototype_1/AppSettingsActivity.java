package com.islarf6546.gmail.lock_prototype_1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AppSettingsActivity extends Activity {

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
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                        switch(position)  {
                            case(0):
                                //new password

                                //start maindraw
                                //ask for password
                                //have submit send an intent back
                                //if intent back is true, ask for create new PW
                                Intent i = new Intent(AppSettingsActivity.this, MainDrawActivity.class);
                                i.putExtra(AppSettingsActivity.this.getString(R.string.change_check_pw), true);
                                startActivityForResult(i, 1);

                        }



                        Intent i = new Intent(AppSettingsActivity.this, MainDrawActivity.class);
                        i.putExtra(getString(R.string.i_newPw), true);
                        AppSettingsActivity.this.startActivity(i);


                        String settingPicked = "Selected: " +
                            String.valueOf(adapterView.getItemAtPosition(position));
                        AndroidHelper.makeToast(AppSettingsActivity.this, settingPicked, false);

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
        if(requestCode == 1)  {
            if(resultCode == Activity.RESULT_OK)  {
                boolean result =  data.getBooleanExtra(this.getString(R.string.change_check_pw), false);

                AndroidHelper.makeToast(this, "Password created", false);
            }
        }
    }
}
