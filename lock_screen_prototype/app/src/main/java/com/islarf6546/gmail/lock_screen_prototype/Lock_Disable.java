package com.islarf6546.gmail.lock_screen_prototype;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Islarf on 17/12/2016.
 */

public class Lock_Disable extends Service {

    static public boolean active = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        active = false;
        System.out.println("|| LOCK_DISABLE HAS BEEN DESTROYED ||");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        active = true;

        Toast.makeText(Lock_Disable.this, "Service Started", Toast.LENGTH_SHORT).show();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        Receiver receiver = new Receiver();
        registerReceiver(receiver, filter);

        return super.onStartCommand(intent, flags, startId);
    }

    static public boolean isActive()  {
        return active;
    }
}
