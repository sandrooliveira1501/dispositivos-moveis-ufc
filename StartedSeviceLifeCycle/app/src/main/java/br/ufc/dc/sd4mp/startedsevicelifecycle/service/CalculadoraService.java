package br.ufc.dc.sd4mp.startedsevicelifecycle.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CalculadoraService extends Service {
    private static final String TAG = "CalculadoraService";
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    public CalculadoraService(){
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return mBinder;
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public CalculadoraService getService() {
            // Return this instance of LocalService so clients can call public methods
            return CalculadoraService.this;
        }
    }

    public double soma(double n1, double n2){
        return n1+n2;
    }

    public double subtracao(double n1, double n2){
        return n1-n2;
    }

    public double multiplicacao(double n1, double n2){
        return n1*n2;
    }

    public double divisao(double n1, double n2) throws IllegalArgumentException{
        if(n2 == 0)
           throw new IllegalArgumentException("Divisão por 0");
        return n1/n2;
    }

}
