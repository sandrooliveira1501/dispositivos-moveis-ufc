package br.ufc.trabalhocomponentesbasicos.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.ufc.trabalhocomponentesbasicos.Constantes;
import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.fragment.AddLinkActivityFragment;

public class AddLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            AddLinkActivityFragment fragment = new AddLinkActivityFragment();
            String url = getIntent().getDataString();
            if (url != null) {
                Bundle params = new Bundle();
                params.putString(Constantes.ARG_URL, url);
                fragment.setArguments(params);
            }
            ft.replace(R.id.container, fragment);
            ft.commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
