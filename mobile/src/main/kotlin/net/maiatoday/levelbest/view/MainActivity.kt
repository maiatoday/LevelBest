package net.maiatoday.levelbest.view

import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.firebase.analytics.FirebaseAnalytics
import io.realm.Realm
import io.realm.Sort
import net.maiatoday.levelbest.LevelBestApplication
import net.maiatoday.levelbest.R
import net.maiatoday.levelbest.databinding.ActivityMainBinding
import net.maiatoday.levelbest.helpers.PreferenceHelper
import net.maiatoday.levelbest.model.Entry
import net.maiatoday.levelbest.view.adapters.EntryRecyclerAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), EntryRecyclerAdapter.OnEntryClick {

    @Inject
    lateinit var prefs: SharedPreferences

    @Inject
    lateinit var analytics: FirebaseAnalytics

    private var firstTime: Boolean = false

    companion object {
        private val REQUEST_NEW_ENTRY = 100
        private val REQUEST_OLD_ENTRY = 101
    }

    private lateinit var realm: Realm

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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LevelBestApplication).component.inject(this)
        realm = Realm.getDefaultInstance()
        firstTime = prefs.getBoolean(PreferenceHelper.KEY_FIRST_TIME, true)
        PreferenceHelper.write(prefs, PreferenceHelper.KEY_FIRST_TIME, false)

        analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navigation = binding.navigation
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setUpRecyclerView()

        val fab = binding.fab
        fab.setOnClickListener { view ->
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id")
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "test")
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
            startActivityForResult(EntryActivity.makeIntent(this, ""), REQUEST_NEW_ENTRY)
        }
    }

    private fun setUpRecyclerView() {
        binding.entriesContent.list.layoutManager = LinearLayoutManager(this)
        binding.entriesContent.list.adapter = EntryRecyclerAdapter(this,
                this,
                realm.where(Entry::class.java).findAllSortedAsync(Entry.TIMESTAMP, Sort.ASCENDING))
        binding.entriesContent.list.setHasFixedSize(true)
        binding.entriesContent.list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun entryClick(view: View, data: Entry) {
        startActivityForResult(EntryActivity.makeIntent(this, data.id), REQUEST_OLD_ENTRY)
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}
