package net.maiatoday.levelbest.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmObject

import net.maiatoday.levelbest.R
import net.maiatoday.levelbest.model.Entry
import net.maiatoday.levelbest.databinding.ActivityEntryBinding
import net.maiatoday.levelbest.model.Mood
import net.maiatoday.levelbest.model.Tag
import java.util.*
import kotlin.properties.Delegates

// see this example https://github.com/realm/realm-java/blob/master/examples/kotlinExample/src/main/kotlin/io/realm/examples/kotlin/KotlinExampleActivity.kt

class EntryActivity : AppCompatActivity() {

    companion object {
        val TAG: String = EntryActivity::class.java.simpleName
    }

    private var realmUI: Realm by Delegates.notNull()
    private var binding: ActivityEntryBinding by Delegates.notNull()
    private var uuid: String = ""

    private var oneEntry: Entry by Delegates.notNull()
    private val entryListener = RealmChangeListener<Entry> { updateUI() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_entry)
        showStatus("Hello MOo!")

        // Open the realm for the UI thread.
        realmUI = Realm.getDefaultInstance()
        if (TextUtils.isEmpty(uuid)) {
            realmUI.executeTransaction({ realm ->
                val tag = realm.createObject(Tag::class.java, UUID.randomUUID().toString())
                tag.title = "Hello"
                val mood = realm.createObject(Mood::class.java, UUID.randomUUID().toString())
                mood.title = "World"
                uuid = UUID.randomUUID().toString()
                val e = realm.createObject(Entry::class.java, uuid)
                e.timeStamp = Date()
                e.sentiment = 7
                e.energyLevel = -2
                e.moods.add(mood)
                e.tags.add(tag)
            }
            )
        }

        oneEntry = searchEntry(realmUI, uuid)
        //Example add a change listener for only this mood
        RealmObject.addChangeListener(oneEntry, entryListener)
        updateUI()
    }

    private fun searchEntry(aRealm: Realm, uuid: String): Entry {
        return aRealm.where(Entry::class.java).equalTo("id", uuid).findFirst()
    }

    override fun onDestroy() {
        super.onDestroy()
        realmUI.close() // Remember to close Realm when done.
    }

    private fun showStatus(txt: String) {
        Log.i(TAG, txt)
        binding.statusText.text = txt
    }

    private fun updateUI() {
        showStatus("Made entry with uuid "+uuid)
    }

}
