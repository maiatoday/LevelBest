package net.maiatoday.levelbest;

import android.app.Application;

import net.maiatoday.levelbest.di.ApplicationModule;
import net.maiatoday.levelbest.di.DaggerApplicationComponent;
import net.maiatoday.levelbest.di.LBComponent;

/**
 * Level Best Application
 * Created by maia on 2016/07/25.
 */

public class LevelBestApplication extends Application {


    private LBComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    public LBComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public LBComponent getComponent() {
        return component;
    }
}
