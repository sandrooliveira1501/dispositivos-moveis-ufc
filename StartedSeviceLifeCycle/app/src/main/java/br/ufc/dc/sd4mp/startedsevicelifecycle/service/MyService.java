package br.ufc.dc.sd4mp.startedsevicelifecycle.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

import br.ufc.dc.sd4mp.startedsevicelifecycle.threads.ThreadCount;

public class MyService extends Service implements Runnable{
    public static final String TAG = "MyService";
    private boolean running = false;
    private ArrayList<ThreadCount> threads = new ArrayList<>();

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        throw null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        //this.running = false;

        for (ThreadCount thread: threads) {
            thread.setRunning(false);
        }

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        /* usando o runnable do MyService
        this.running = true;
        new Thread(this).start();
        */

        //usando uma classe de thread separada

        ThreadCount thread = new ThreadCount();
        thread.setRunning(true);
        thread.start();
        threads.add(thread);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void run() {
            int i = 0;
            while (running && i <= 10) {
                i++;
                Log.i(TAG, "MyService is running!!! " + i);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
            running = false;
    }
}
