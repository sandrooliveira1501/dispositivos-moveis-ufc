package br.ufc.dc.sd4mp.startedsevicelifecycle.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.ufc.dc.sd4mp.startedsevicelifecycle.R;
import br.ufc.dc.sd4mp.startedsevicelifecycle.service.MyService;
import br.ufc.dc.sd4mp.startedsevicelifecycle.service.MyServiceTutorial2;
import br.ufc.dc.sd4mp.startedsevicelifecycle.service.ReceiverStartService;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private Button startService, stopService;
    private Button stopServiceTutorial2;
    private Button btTutorial3;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button) findViewById(R.id.btStartService);
        stopService = (Button) findViewById(R.id.btStopService);

        startService.setOnClickListener(new OnClickListenerFirstTutorialStart());
        stopService.setOnClickListener(new OnClickListenerFirstTutorialStop());

        Log.i(TAG, "Registrando receiver eventos de tela");

        //Registrando broadcast receiver do tutorial 2

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new ReceiverStartService();
        registerReceiver(mReceiver, filter);

        stopServiceTutorial2 = (Button) findViewById(R.id.bt2StopService);
        stopServiceTutorial2.setOnClickListener(new OnClickListenerSecondTutorialStop());

        //Inciar Activity Tutorial 3

        btTutorial3 = (Button) findViewById(R.id.btTutorial3);
        btTutorial3.setOnClickListener(new OnClickListenerTutorial3());

    }


    private class OnClickListenerFirstTutorialStart implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i(TAG, "Iniciando Service pelo click do botão start");

            Intent intent = new Intent(MainActivity.this, MyService.class);
            startService(intent);

            toast("Serviço Tutorial 1 Iniciado");
        }
    }

    private class OnClickListenerFirstTutorialStop implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i(TAG, "Parando Service pelo click do botão stop");

            Intent intent = new Intent(MainActivity.this, MyService.class);
            stopService(intent);

            toast("Serviço Tutorial 1 Parado");
        }
    }

    private class OnClickListenerSecondTutorialStop implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i(TAG, "Parando Service pelo click do botão stop");
            stopServiceTutorial2();
            toast("Serviço Tutorial 2 Parado");

        }
    }

    private class OnClickListenerTutorial3 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i(TAG, "Iniciando Activity Tutorial 03");

            Intent intent = new Intent(MainActivity.this, Tutorial3Activity.class);
            startActivity(intent);

        }
    }


    public void stopServiceTutorial2(){
        Intent intent = new Intent(getApplicationContext(), MyServiceTutorial2.class);
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy Activity");

        stopServiceTutorial2();
        if(mReceiver != null)
            unregisterReceiver(mReceiver);

        super.onDestroy();
    }

    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
