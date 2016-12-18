package com.islarf6546.gmail.lock_screen_prototype;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Islarf on 18/12/2016.
 */

public class Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("|| onReceive happened ||");

        String action = intent.getAction();
        if(action.equals(Intent.ACTION_SCREEN_OFF))  {
            System.out.println("|| Screen off ||");

            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}