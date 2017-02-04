package net.maiatoday.levelbest.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

import net.maiatoday.levelbest.LevelBestApplication;
import net.maiatoday.levelbest.R;
import net.maiatoday.levelbest.databinding.ActivityMainBinding;
import net.maiatoday.levelbest.helpers.PreferenceHelper;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    SharedPreferences prefs;

    @Inject
    FirebaseAnalytics analytics;

    private boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((LevelBestApplication)getApplication()).getComponent().inject(this);
        firstTime = prefs.getBoolean(PreferenceHelper.KEY_FIRST_TIME, true);
        PreferenceHelper.write(prefs, PreferenceHelper.KEY_FIRST_TIME, false);
        analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "test");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                if (firstTime) {
                    Snackbar.make(view, "First Time - Replace with your own action "+utils.DemoUtils.addTest(2,4), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Replace with your own action "+utils.DemoUtils.addTest(4,4), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
