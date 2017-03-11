package com.islarf6546.gmail.lock_prototype_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by islarf on 10/03/17.
 */

public class Receiver extends BroadcastReceiver {




    @Override
    public void onReceive(Context ctx, Intent i)  {

        System.out.println("RECIEVED??");

        String action = i.getAction();
        if(action.equals(Intent.ACTION_SCREEN_OFF))  {
            Intent changeIntent = new Intent(ctx, MainDrawActivity.class);
            changeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(changeIntent);

            System.out.println("ACTION SCREEN OFF");
        }

        Intent changeIntent = new Intent(ctx, LockListenerService.class);
        ctx.startService(changeIntent);

    }
}
