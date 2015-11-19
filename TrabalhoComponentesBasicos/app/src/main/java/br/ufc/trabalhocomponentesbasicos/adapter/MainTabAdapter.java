package br.ufc.trabalhocomponentesbasicos.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.ufc.trabalhocomponentesbasicos.fragment.LinksFragment;
import br.ufc.trabalhocomponentesbasicos.fragment.TopicosFragment;

/**
 * Created by alexsandro on 17/11/15.
 */
public class MainTabAdapter extends FragmentPagerAdapter {

    private Context context;
    static final int QTD_TABS = 2;
    private static final String[] tabTitles= {"Tópicos","Todos Links"};

    public MainTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            TopicosFragment topicosFragment = new TopicosFragment();
            return topicosFragment;
        }else{
            return new LinksFragment();
        }

    }

    @Override
    public int getCount() {
        return QTD_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
