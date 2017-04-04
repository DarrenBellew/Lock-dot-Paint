package com.islarf6546.gmail.lock_prototype_1;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/*
* Author: Darren Bellew
*
* This class is a Service which initialises a receiver that listens for when the phone is locked.
* The class then initialises LockScreenActivity
*/


public class LockListenerService extends Service {

    Receiver receiver;
    private static String TAG = "lock_screen_service";

    public static boolean isRunning = false;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId)  {
        /*
        * This method is ran when the service starts.
        * It sets a isrunning flag, and registers the receiver to listen for Action Screen Off
        */

        isRunning = true;

        super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "FirstService Started");


        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        receiver = new Receiver();
        registerReceiver(receiver, filter);

        return START_STICKY;
    }

    @Override
    public void onDestroy()  {
        /*
        * This method activates when the service is destroyed.
        * It unregisteres the receiver and sets the isrunning to false.
        */
        unregisterReceiver(receiver);

        isRunning = false;
        super.onDestroy();
        Log.d(TAG, "FirstService destroyed");
    }

    public static boolean isRunning()  {
        return isRunning;
    }

}
