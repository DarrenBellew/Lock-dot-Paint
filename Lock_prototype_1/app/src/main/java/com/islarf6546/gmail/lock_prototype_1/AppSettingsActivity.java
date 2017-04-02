package com.islarf6546.gmail.lock_prototype_1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class AppSettingsActivity extends Activity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    View enableDisableView;
    ArrayList<String> settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.getActionBar().setTitle(R.string.settingsScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);


        //TESTING. DELETE.
        //PasswordHelper.resetPassword(this);

        settings = SettingsHandler.getOptions(this, LockListenerService.isRunning());


        final ListAdapter theAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, settings);

        final ListView settingsList = (ListView) findViewById(R.id.settingsListView);


        settingsList.setAdapter(theAdapter);


        settingsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        Intent i;
                        switch (position) {
                            case (0):
                                if (PasswordHelper.isPwSet(AppSettingsActivity.this)) {

                                    i = new Intent(AppSettingsActivity.this, LockScreenActivity.class);
                                    i.putExtra(getString(R.string.checkPw), true);
                                    i.putExtra(getString(R.string.actionBar), getString(R.string.checkPw));
                                    enableDisableView = view;
                                    AppSettingsActivity.this.startActivityForResult(i, 1);

                                } else {
                                    AndroidHelper.makeToast(AppSettingsActivity.this, AppSettingsActivity.this.getString(R.string.createPwWarn), false);
                                }
                                break;
                            case (1):
                                if (PasswordHelper.isPwSet(AppSettingsActivity.this)) {
                                    if (LockListenerService.isRunning() && PasswordHelper.isPwSet(AppSettingsActivity.this)) {

                                        i = new Intent(AppSettingsActivity.this, LockScreenActivity.class);
                                        i.putExtra(AppSettingsActivity.this.getString(R.string.checkPw), true);
                                        i.putExtra(getString(R.string.actionBar), getString(R.string.disable_Lockscreen));

                                        setEnableDisableView(view);

                                        AppSettingsActivity.this.startActivityForResult(i, 2);


                                    } else {

                                        startService(new Intent(AppSettingsActivity.this, LockListenerService.class));
                                        ((TextView) view).setText(AppSettingsActivity.this.getText(R.string.disablePwOpt));
                                    }
                                } else {
                                    AndroidHelper.makeToast(AppSettingsActivity.this, AppSettingsActivity.this.getString(R.string.createPwWarn), false);
                                }
                                break;

                            case (2):
                                if (PasswordHelper.isPwSet(AppSettingsActivity.this)) {
                                    i = new Intent(AppSettingsActivity.this, LockScreenActivity.class);
                                    i.putExtra(getString(R.string.actionBar), getString(R.string.testTitle));
                                    i.putExtra(AppSettingsActivity.this.getString(R.string.testTitle), true);
                                    AppSettingsActivity.this.startActivity(i);
                                } else {
                                    AndroidHelper.makeToast(AppSettingsActivity.this, AppSettingsActivity.this.getString(R.string.createPwWarn), false);
                                }
                                break;
                            case (3):

                                if(!PasswordHelper.isPwSet(AppSettingsActivity.this)) {
                                    i = new Intent(AppSettingsActivity.this, LockScreenActivity.class);
                                    i.putExtra(AppSettingsActivity.this.getString(R.string.change_pw), true);
                                    i.putExtra(getString(R.string.actionBar), getString(R.string.change_pw));
                                    AppSettingsActivity.this.startActivity(i);
                                }
                                else  {
                                    AndroidHelper.makeToast(AppSettingsActivity.this, getString(R.string.passwordAlready), false);
                                }


                                break;
                        }
                    }
                });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        System.out.println("RESULT");
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                boolean result = data.getBooleanExtra(this.getString(R.string.checkPw_confirm), false);
                if (result) {
                    Intent i = new Intent(AppSettingsActivity.this, LockScreenActivity.class);
                    i.putExtra(AppSettingsActivity.this.getString(R.string.change_pw), true);
                    i.putExtra(AppSettingsActivity.this.getString(R.string.actionBar), AppSettingsActivity.this.getString(R.string.change_pw));
                    AppSettingsActivity.this.startActivity(i);
                }
            }
        }
        else if(requestCode == 2)  {
            if(resultCode == Activity.RESULT_OK)  {
                boolean result = data.getBooleanExtra(this.getString(R.string.checkPw_confirm), false);

                System.out.println("DATA: " + data.getExtras());

                System.out.println("result: " + result + "\ngetString(R.string.checkPw_confirm): " + getString(R.string.checkPw_confirm));

                if(result)  {
                    stopService(new Intent(this, LockListenerService.class));
                    ((TextView) enableDisableView).setText(AppSettingsActivity.this.getText(R.string.enablePwOpt));
                }



            }

        }
        else {
            System.out.println("ERROR MAYBE -> REQUEST CODE: " + requestCode);
        }
    }

    private void setEnableDisableView(View view)  {
        enableDisableView = view;
    }


    private void resetActivity(boolean fin)  {
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
        if(fin)  {
            finish();
        }


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("AppSettings Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }*/
}
