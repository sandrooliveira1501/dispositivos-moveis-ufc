package br.ufc.trabalhocomponentesbasicos.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.ufc.trabalhocomponentesbasicos.Constantes;
import br.ufc.trabalhocomponentesbasicos.R;
import br.ufc.trabalhocomponentesbasicos.SettingsApplication;
import br.ufc.trabalhocomponentesbasicos.adapter.MainTabAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainTabAdapter mainTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SettingsApplication application = (SettingsApplication) getApplication();
        if(application.getBackgroundImage() != null){
            TextView tvBackground = (TextView) findViewById(R.id.background);
            tvBackground.setBackgroundResource(application.getBackgroundImage());
        }

        viewPager = (ViewPager) findViewById(R.id.viewPagerTabs);
        //todo review
        viewPager.setOffscreenPageLimit(2);

        mainTabAdapter = new MainTabAdapter(getSupportFragmentManager(), this.getBaseContext());
        viewPager.setAdapter(mainTabAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        int corUnselected = getResources().getColor(R.color.colorPrimaryDark);
        int corSelected = getResources().getColor(R.color.white);
        tabLayout.setTabTextColors(corUnselected, corSelected);//mudar cor de tab selecionada
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_link){
            Log.i(Constantes.LOG_MENU, "Menu Link");
            Intent intent = new Intent(this, AddLinkActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_add_topico){
            Log.i(Constantes.LOG_MENU, "Menu Tópico");
            Intent intent = new Intent(this, AddTopicoActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_background){
            Log.i(Constantes.LOG_MENU, "Background");
            Intent intent = new Intent(this, EscolherPlanoDeFundoActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SettingsApplication application = (SettingsApplication) getApplication();
        if(application.getBackgroundImage() != null){
            TextView tvBackground = (TextView) findViewById(R.id.background);
            tvBackground.setBackgroundResource(application.getBackgroundImage());
            tvBackground.setAlpha(0.3f);
        }
    }
}
