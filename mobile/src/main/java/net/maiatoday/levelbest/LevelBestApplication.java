package net.maiatoday.levelbest;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import net.maiatoday.levelbest.di.ApplicationModule;
import net.maiatoday.levelbest.di.DaggerApplicationComponent;
import net.maiatoday.levelbest.di.LBComponent;

import io.realm.Realm;

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
        Realm.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
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
