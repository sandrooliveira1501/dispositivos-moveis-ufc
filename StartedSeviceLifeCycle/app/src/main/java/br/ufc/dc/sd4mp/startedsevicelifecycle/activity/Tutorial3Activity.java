package br.ufc.dc.sd4mp.startedsevicelifecycle.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufc.dc.sd4mp.startedsevicelifecycle.R;
import br.ufc.dc.sd4mp.startedsevicelifecycle.service.CalculadoraService;

public class Tutorial3Activity extends AppCompatActivity {
    private static final String TAG = "Tutorial3Activity";
    private CalculadoraService mService;
    private boolean mBound = false;

    private EditText etNumero1, etNumero2;
    private Button btSoma, btSubtracao, btMultiplicacao, btDivisao;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_tutorial3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNumero1 = (EditText) findViewById(R.id.etNumero1);
        etNumero2 = (EditText) findViewById(R.id.etNumero2);

        btSoma = (Button) findViewById(R.id.btSoma);
        btSoma.setOnClickListener(onClickButton);
        btSubtracao= (Button) findViewById(R.id.btSubtracao);
        btSubtracao.setOnClickListener(onClickButton);
        btMultiplicacao = (Button) findViewById(R.id.btMultiplicacao);
        btMultiplicacao.setOnClickListener(onClickButton);
        btDivisao = (Button) findViewById(R.id.btDivisao);
        btDivisao.setOnClickListener(onClickButton);

        tvResultado = (TextView) findViewById(R.id.tvResultado);

    }

    public boolean validarCampos(){

        String numero1 = etNumero1.getText().toString().trim();
        String numero2 = etNumero2.getText().toString().trim();
        boolean retorno = true;

        if(numero1.equals("")){
            etNumero1.setError("Preencha Campo");
            retorno = false;
        }

        if(numero2.equals("")){
            etNumero2.setError("Preencha Campo");
            retorno = false;
        }


        return retorno;
    }


    private View.OnClickListener onClickButton = new View.OnClickListener(){

        @Override
        public void onClick(View v) {


            if(!validarCampos()){
                return;
            }

            double numero1;
            double numero2;
            try{
                numero1 = Double.parseDouble(etNumero1.getText().toString().trim());
                numero2 = Double.parseDouble(etNumero2.getText().toString().trim());
            }catch (NumberFormatException ex){
                toast("Números inválidos");
                return;
            }

            double resultado = 0;

            switch (v.getId()){
                case R.id.btSoma :
                    resultado = mService.soma(numero1,numero2);
                    break;
                case R.id.btSubtracao :
                    resultado = mService.subtracao(numero1,numero2);
                    break;
                case R.id.btMultiplicacao :
                    resultado = mService.multiplicacao(numero1,numero2);
                    break;
                case R.id.btDivisao :
                    try {
                        resultado = mService.divisao(numero1, numero2);
                    }catch (IllegalArgumentException ex){
                        toast("Divisor não pode ser 0!");
                    }
                    break;
            }

            tvResultado.setText(resultado + "");

        }
    };

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");
        if(!mBound) {
            Intent intent = new Intent(this, CalculadoraService.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        Log.i(TAG, "Desfazendo ligação service");
        if(mBound){
            unbindService(mConnection);
            mBound = false;
        }

        super.onDestroy();
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.i(TAG, "Service Connected");
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            CalculadoraService.LocalBinder binder = (CalculadoraService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            Log.i(TAG, "Service Disconnected");

            mBound = false;
        }
    };

    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
