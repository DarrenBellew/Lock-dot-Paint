package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by islarf on 04/03/17.
 */

public class SettingsHandler {


    public static ArrayList<String> getOptions(Context ctx, boolean serviceRunning)  {

        ArrayList<String> options = new ArrayList<>();
        options.add(ctx.getString(R.string.changePwOpt));
        if(serviceRunning)  {
            options.add(ctx.getString(R.string.disablePwOpt));
        }
        else  {
            options.add(ctx.getString(R.string.enablePwOpt));
        }
        options.add(ctx.getString(R.string.testPwOpt));

        if(!PasswordHelper.isPwSet(ctx)) {
            options.add(ctx.getString(R.string.createPwOpt));
        }
        else  {

        }
        return options;
    }

}
