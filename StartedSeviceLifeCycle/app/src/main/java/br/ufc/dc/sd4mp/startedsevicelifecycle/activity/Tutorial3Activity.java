package br.ufc.dc.sd4mp.startedsevicelifecycle.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.ufc.dc.sd4mp.startedsevicelifecycle.R;

public class Tutorial3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
