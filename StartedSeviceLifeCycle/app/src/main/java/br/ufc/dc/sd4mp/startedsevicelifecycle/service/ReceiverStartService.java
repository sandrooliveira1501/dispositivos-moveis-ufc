package br.ufc.dc.sd4mp.startedsevicelifecycle.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.ufc.dc.sd4mp.startedsevicelifecycle.activity.MainActivity;

public class ReceiverStartService extends BroadcastReceiver {
    public ReceiverStartService() {
    }

    public static final String TAG = "ReceiverStartService";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.i(TAG, "Boot");
        }else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            Log.i(TAG, "Screen On");
        }else{
            Log.i(TAG, intent.getAction());
            return;
        }
        Log.i(TAG, "Chamando serviço");

        Intent i = new Intent(context, MyServiceTutorial2.class);
        context.startService(i);
    }

}
