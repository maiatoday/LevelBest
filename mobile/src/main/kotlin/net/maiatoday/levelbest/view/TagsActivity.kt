package net.maiatoday.levelbest.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import net.maiatoday.levelbest.R

class TagsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tags)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        fun makeIntent(context: Context): Intent {
            val i: Intent = Intent(context, TagsActivity::class.java)
            return i
        }
    }

}
