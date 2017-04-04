package com.islarf6546.gmail.lock_prototype_1;




import android.content.Context;
import android.widget.Toast;

/**
 * Author: Darren Bellew
 *
 * This class contains (a) static function to make executing basic android functions easier.
 */

public class AndroidHelper {
    public static void makeToast(Context ctx, String text, boolean length_long)  {
        /*
        * Run a toast; long / short depending on boolean
         */
        if(length_long)  {
            Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();
        }
        else  {
            Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
        }

    }


}
