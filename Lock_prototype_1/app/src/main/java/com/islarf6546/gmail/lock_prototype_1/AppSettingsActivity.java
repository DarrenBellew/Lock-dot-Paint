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

/*
* Author: Darren Bellew
*
* This class is an activity for a list view which acts as a settings screen for the application.
*
* The buttons are accessed through a switch/case for their position on the settings screen.
* The settings text is retrieved into a String arraylist from SettingsHandler which reads the strings from strings.xml.
*
*
* ListView tutorial used: https://www.youtube.com/watch?v=rhj4_KBD6BQ
* List view documentation: https://developer.android.com/guide/topics/ui/layout/listview.html
*
* Intents documentation: https://developer.android.com/guide/components/intents-filters.html
* onActivityResult tutorial: http://stackoverflow.com/questions/920306/sending-data-back-to-the-main-activity-in-android
*/


public class AppSettingsActivity extends Activity {

    View enableDisableView;
    ArrayList<String> settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.getActionBar().setTitle(R.string.settingsScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);


        settings = SettingsHandler.getOptions(this, LockListenerService.isRunning());


        final ListAdapter theAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, settings);

        final ListView settingsList = (ListView) findViewById(R.id.settingsListView);

        settingsList.setAdapter(theAdapter);

        //when an item is clicked, this gets called.
        settingsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        Intent i;
                        //a switch to go through the positions on the settings list. (Order is important).
                        switch (position) {
                            case (0):
                                /*
                                * CHANGE PASSWORD
                                *
                                * starts lockscreenactivity, and waits for result (1).
                                * It is only looking for a confirmation of the current password, before allowing the user to change.
                                *
                                * Will only run if a password has been set. otherwise displays a toast.
                                */

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
                                /*
                                * ENABLE/DISABLE Password
                                * Enable, starts the service that activates the lockscreenactivity when the device is locked.
                                * Disable, requests a password confirmation, then on success closes the service.
                                *
                                * Waiting for response code: 2
                                */

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
                                /*
                                * This option (if password is set) opens the lockscreenactivity and allows the user to practice / test their created password.
                                 */
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

                                /*
                                * This option only appears the first time, and if the user hasn't created a password, and allows the user to create their first password.
                                * If the user has already created a password but hasn't closed the app yet, it creates a toast saying the password is already created.
                                 */

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        System.out.println("RESULT");
        if (requestCode == 1) {
            /*
            * Result from change password. Checks if the password is correct by checking the boolean value of R.string.checkPw_confirm
            *
            * if correct it reactivates LockScreenActivity, however sends the flag to change the password.
             */
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
            /*
            * Enable/Disable password uses request code 2. This is the confirmation of the disabling of the lockscreen.
             */
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
            //TESTING || If any other request code somehow gets returned.
            System.out.println("ERROR MAYBE -> REQUEST CODE: " + requestCode);
        }
    }

    //used with the enable/disable view.
    private void setEnableDisableView(View view)  {
        enableDisableView = view;
    }

}
