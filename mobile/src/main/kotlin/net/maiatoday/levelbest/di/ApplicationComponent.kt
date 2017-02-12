package net.maiatoday.levelbest.di

import javax.inject.Singleton

import dagger.Component

/**
 * Application Component for dependency injection
 * Created by maia on 2016/07/25.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent : LBComponent
