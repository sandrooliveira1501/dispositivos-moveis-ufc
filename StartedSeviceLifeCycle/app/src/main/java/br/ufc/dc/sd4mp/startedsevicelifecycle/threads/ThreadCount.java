package br.ufc.dc.sd4mp.startedsevicelifecycle.threads;

import android.util.Log;

/**
 * Created by alexsandro on 08/12/15.
 */
public class ThreadCount extends Thread {

    public static final String TAG = "ThreadCount";

    private boolean running = false;

    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean isRunning(){
        return this.running;
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
