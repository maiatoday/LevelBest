package net.maiatoday.levelbest.di

import dagger.Component
import net.maiatoday.levelbest.view.EntryActivity
import net.maiatoday.levelbest.view.MainActivity
import javax.inject.Singleton

/**
 * Component for injection
 * Created by maia on 2016/07/25.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class)
)
interface LBComponent {
    fun inject(target: MainActivity)
    fun inject(target: EntryActivity)
}
