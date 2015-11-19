package br.ufc.trabalhocomponentesbasicos;

import android.app.Application;

/**
 * Created by alexsandro on 19/11/15.
 */

public class SettingsApplication extends Application {

    private static SettingsApplication instance = null;
    private Integer backgroundImage = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public SettingsApplication getInstance(){
        return instance;
    }

    public Integer getBackgroundImage(){
        return backgroundImage;
    }

    public void setBackgroundImage(Integer background){
        this.backgroundImage = background;
    }

}
