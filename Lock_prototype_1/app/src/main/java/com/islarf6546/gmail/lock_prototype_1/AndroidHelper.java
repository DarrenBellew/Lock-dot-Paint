package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by islarf on 28/02/17.
 */

public class AndroidHelper {
    /*
        Just a basic class to allow me to make a toast in an easier fashion.
        And also allow it to perform other helpful android functions if necessary.
    */
    public static void makeToast(Context ctx, String text, boolean length_long)  {

        if(length_long)  {
            Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();
        }
        else  {
            Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
        }

    }


}
