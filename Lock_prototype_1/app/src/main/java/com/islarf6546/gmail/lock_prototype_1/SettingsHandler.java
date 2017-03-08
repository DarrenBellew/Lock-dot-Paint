package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;

/**
 * Created by islarf on 04/03/17.
 */

public class SettingsHandler {

    private static String[] options;

    public static String[] getOptions(Context ctx)  {
        options = new String[]  {
                ctx.getString(R.string.change_pw_check),
                ctx.getString(R.string.enable_Lockscreen)
        };

        return options;
    }

    public static void executeAction(int position, Context ctx)  {
        options = getOptions(ctx);

        switch(position)  {
            case(0):

                break;
            default:
                break;
        }
    }
}
