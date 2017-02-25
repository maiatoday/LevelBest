package net.maiatoday.levelbest.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import net.maiatoday.levelbest.R
import net.maiatoday.levelbest.databinding.ActivityTagsBinding

class TagsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTagsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tags)
        setSupportActionBar(binding.tagsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.all_the_tags)
    }

    companion object {
        fun makeIntent(context: Context): Intent {
            val i: Intent = Intent(context, TagsActivity::class.java)
            return i
        }
    }

}
