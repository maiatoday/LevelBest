package net.maiatoday.levelbest.helpers

import android.content.SharedPreferences

/**
 * Created by maia on 2016/07/25.
 */

object PreferenceHelper {
    val SHARED_PREF_NAME = "LevelBest"
    val KEY_FIRST_TIME = "first_time"

    fun write(prefs: SharedPreferences, key: String, value: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }
}
