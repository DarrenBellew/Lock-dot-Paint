package com.islarf6546.gmail.lock_prototype_1;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

public class LockListenerService extends Service {


    private static String TAG = "lock_screen_service";

    public static boolean isRunning = false;

    public LockListenerService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;

        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)  {
        isRunning = true;

        super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "FirstService Started");
        this.stopSelf();

        System.out.println("SERVICE START COMMAND: "
                + "\nintent: " + intent
                + "\nflags: " + flags
                + "\nstartId: " + startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()  {
        //TODO: Auto-generated method stub
        isRunning = false;
        super.onDestroy();
        Log.d(TAG, "FirstService destroyed");
    }

    public static boolean isRunning()  {
        return isRunning;
    }
}
