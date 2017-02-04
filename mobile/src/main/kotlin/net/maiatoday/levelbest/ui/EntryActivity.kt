package net.maiatoday.levelbest.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm

import net.maiatoday.levelbest.R
import net.maiatoday.levelbest.databinding.ActivityEntryBinding
import kotlin.properties.Delegates

// see this example https://github.com/realm/realm-java/blob/master/examples/kotlinExample/src/main/kotlin/io/realm/examples/kotlin/KotlinExampleActivity.kt

class EntryActivity : AppCompatActivity() {

    private var realm: Realm by Delegates.notNull()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityEntryBinding = DataBindingUtil.setContentView(this, R.layout.activity_entry)
        binding.statusText.text = "Hello MOo!"

        // Open the realm for the UI thread.
        realm = Realm.getDefaultInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close() // Remember to close Realm when done.
    }
}
