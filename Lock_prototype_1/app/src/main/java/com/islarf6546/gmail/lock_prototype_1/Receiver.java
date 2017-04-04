package com.islarf6546.gmail.lock_prototype_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
* Author: Darren Bellew
*
* This class extends BroadcastReceiver and is initialised by LockListenerService.
* It's purpose is to listen fo "ACTION_SCREEN_OFF" and activates the LockScreenActivity (if not already started)
*
* BroadcastReceiver documentation: https://developer.android.com/reference/android/content/BroadcastReceiver.html
*
* See the forum post link in LockListenerService header.
*/

public class Receiver extends BroadcastReceiver {




    @Override
    public void onReceive(Context ctx, Intent i)  {

        System.out.println("RECIEVED??");

        String action = i.getAction();
        if(action.equals(Intent.ACTION_SCREEN_OFF) && !LockScreenActivity.isRunning())  {
            System.out.println("ACTION SCREEN OFF REQUEST RECEIVED");

            Intent changeIntent = new Intent(ctx, LockScreenActivity.class);
            changeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(changeIntent);
        }

    }
}
