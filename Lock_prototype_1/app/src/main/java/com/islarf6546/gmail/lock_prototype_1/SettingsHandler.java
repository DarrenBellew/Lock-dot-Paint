package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;

import java.util.ArrayList;

/*
* Author: Darren Bellew
*
* This class gets the strings to be used on the settings screen from strings.xml and returns it in an ArrayList<String>.
*/

public class SettingsHandler {


    public static ArrayList<String> getOptions(Context ctx, boolean serviceRunning)  {

        ArrayList<String> options = new ArrayList<>();
        options.add(ctx.getString(R.string.changePwOpt));

        //Disable or Enable depending on if the service is currently running.
        if(serviceRunning)  {
            options.add(ctx.getString(R.string.disablePwOpt));
        }
        else  {
            options.add(ctx.getString(R.string.enablePwOpt));
        }
        options.add(ctx.getString(R.string.testPwOpt));

        //this should only appear on the initial launch of the application; or if the password isn't set yet.
        if(!PasswordHelper.isPwSet(ctx)) {
            options.add(ctx.getString(R.string.createPwOpt));
        }
        return options;
    }

}
