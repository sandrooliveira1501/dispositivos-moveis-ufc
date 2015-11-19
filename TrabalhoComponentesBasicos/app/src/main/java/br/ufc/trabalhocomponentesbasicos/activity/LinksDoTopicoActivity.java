package br.ufc.trabalhocomponentesbasicos.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.fragment.LinksDoTopicoActivityFragment;

public class LinksDoTopicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links_do_topico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            LinksDoTopicoActivityFragment fragment = new LinksDoTopicoActivityFragment();
            Bundle args = getIntent().getExtras();
            if(args != null) {
                fragment.setArguments(args);
            }
            ft.replace(R.id.container, fragment);
            ft.commit();
        }

    }

}
