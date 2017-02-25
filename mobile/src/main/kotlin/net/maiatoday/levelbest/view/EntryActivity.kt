package net.maiatoday.levelbest.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import net.maiatoday.levelbest.LevelBestApplication
import net.maiatoday.levelbest.R
import net.maiatoday.levelbest.databinding.ActivityEntryBinding
import net.maiatoday.levelbest.viewmodel.EntryViewModel
import javax.inject.Inject

// see this example https://github.com/realm/realm-java/blob/master/examples/kotlinExample/src/main/kotlin/io/realm/examples/kotlin/KotlinExampleActivity.kt

class EntryActivity : AppCompatActivity() {

    companion object {
        val TAG: String = EntryActivity::class.java.simpleName
        val KEY_UUID: String = EntryActivity::class.java.simpleName+".key.uuid"

        fun makeIntent(context: Context, uuid:String): Intent {
            val i: Intent = Intent(context, EntryActivity::class.java)
            i.putExtra(KEY_UUID, uuid)
            return i
        }
    }

    @Inject
    lateinit var prefs: SharedPreferences

    @Inject
    lateinit var analytics: FirebaseAnalytics

    private lateinit var binding: ActivityEntryBinding
//    private var uuid: String = "3a89e607-75ab-4029-aa7f-2a2dfab8bfe0"
    private var uuid: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LevelBestApplication).component.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_entry)
        getIntentParameters(savedInstanceState)
        binding.viewModel = EntryViewModel(uuid)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        updateUI()
    }

    private fun getIntentParameters(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            uuid = intent.getStringExtra(KEY_UUID)
        } else {
            uuid = savedInstanceState.getString(KEY_UUID)
        }
        if (uuid.isBlank()) {
            uuid = EntryViewModel.makeNewEntry()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_UUID, uuid)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewModel.close()
    }

    private fun showStatus(txt: String) {
        Log.i(TAG, txt)
        binding.statusText.text = txt
    }

    private fun updateUI() {
        showStatus("Made entry with uuid "+uuid)
    }

}
