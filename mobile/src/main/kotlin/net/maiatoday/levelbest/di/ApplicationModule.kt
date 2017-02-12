package net.maiatoday.levelbest.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import com.google.firebase.analytics.FirebaseAnalytics

import net.maiatoday.levelbest.helpers.PreferenceHelper

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Application Module for dependency injection
 * Created by maia on 2016/07/25.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun providesContext(): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PreferenceHelper.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    internal fun provideAnalytics(context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }
}