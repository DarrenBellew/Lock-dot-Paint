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

        this.getActionBar().setTitle(R.string.settingsScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        String[] settings = {
                "New Password", "enable/disable lockscreen", "Test Password", "CREATE Password"
        };

        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                settings);

        ListView settingsList = (ListView) findViewById(R.id.settingsListView);

        settingsList.setAdapter(theAdapter);

        settingsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        Intent i;
                        switch(position)  {
                            case(0):
                                //new password

                                //start maindraw
                                //ask for password
                                //have submit send an intent back
                                //if intent back is true, ask for create new PW
                                i = new Intent(AppSettingsActivity.this, MainDrawActivity.class);
                                //i.putExtra(AppSettingsActivity.this.getString(R.string.change_pw_check), true);
                                i.putExtra(AppSettingsActivity.this.getString(R.string.change_pw_check), true);
                                i.putExtra(getString(R.string.actionBar), getString(R.string.change_pw_check));
                                AppSettingsActivity.this.startActivityForResult(i, 1);
                                break;
                            case(1):
                                if(LockListenerService.isRunning())  {
                                    stopService(new Intent(AppSettingsActivity.this, LockListenerService.class));
                                }
                                else  {
                                    startService(new Intent(AppSettingsActivity.this, LockListenerService.class));
                                }

                            case(2):
                                i = new Intent(AppSettingsActivity.this, MainDrawActivity.class);
                                i.putExtra(getString(R.string.actionBar), getString(R.string.testTitle));
                                i.putExtra(AppSettingsActivity.this.getString(R.string.testTitle), true);
                                AppSettingsActivity.this.startActivity(i);
                                break;
                            case(3):
                                i = new Intent(AppSettingsActivity.this, MainDrawActivity.class);
                                i.putExtra(AppSettingsActivity.this.getString(R.string.change_pw), true);
                                i.putExtra(getString(R.string.actionBar), getString(R.string.change_pw));
                                AppSettingsActivity.this.startActivity(i);
                                break;
                        }

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  {

        System.out.println("RESULT");
        if(requestCode == 1)  {
            if(resultCode == Activity.RESULT_OK)  {
                boolean result =  data.getBooleanExtra(this.getString(R.string.change_pw_check), false);
                if(result)  {
                    Intent i = new Intent(AppSettingsActivity.this, MainDrawActivity.class);
                    i.putExtra(AppSettingsActivity.this.getString(R.string.change_pw), true);
                    i.putExtra(AppSettingsActivity.this.getString(R.string.actionBar), AppSettingsActivity.this.getString(R.string.change_pw));
                    AppSettingsActivity.this.startActivity(i);
                }
            }
        }
        else  {
            System.out.println("ERROR MAYBE -> REQUEST CODE: " + requestCode);
        }
    }
}
