package net.maiatoday.levelbest.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import net.maiatoday.levelbest.helpers.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application Module for dependency injection
 * Created by maia on 2016/07/25.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(PreferenceHelper.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }
}