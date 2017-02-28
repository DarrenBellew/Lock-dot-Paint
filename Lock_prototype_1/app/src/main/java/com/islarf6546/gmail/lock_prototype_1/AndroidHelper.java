package com.islarf6546.gmail.lock_prototype_1;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by islarf on 28/02/17.
 */

public class AndroidHelper {


    public static void makeToast(Context ctx, String text, boolean length)  {

        if(length)  {
            Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();
        }
        else  {
            Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
        }

    }
}
