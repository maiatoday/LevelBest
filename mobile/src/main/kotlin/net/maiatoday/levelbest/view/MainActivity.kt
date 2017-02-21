package net.maiatoday.levelbest.view

import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.google.firebase.analytics.FirebaseAnalytics

import net.maiatoday.levelbest.LevelBestApplication
import net.maiatoday.levelbest.R
import net.maiatoday.levelbest.databinding.ActivityMainBinding
import net.maiatoday.levelbest.helpers.PreferenceHelper
import utils.addTest

import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var prefs: SharedPreferences

    @Inject
    lateinit var analytics: FirebaseAnalytics

    private var firstTime: Boolean = false

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_moods -> {
                startActivity(MoodsActivity.makeIntent(this))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tags -> {
                startActivity(TagsActivity.makeIntent(this))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LevelBestApplication).component.inject(this)
        firstTime = prefs.getBoolean(PreferenceHelper.KEY_FIRST_TIME, true)
        PreferenceHelper.write(prefs, PreferenceHelper.KEY_FIRST_TIME, false)

        analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navigation = binding.navigation
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fab = binding.fab
        fab.setOnClickListener { view ->
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id")
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "test")
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
            if (firstTime) {
                Snackbar.make(view, "First Time - Replace with your own action " + addTest(2, 4), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            } else {
                Snackbar.make(view, "Replace with your own action " + addTest(4, 4), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

            }
            startActivity(Intent(this@MainActivity, EntryActivity::class.java))
        }
    }
}
