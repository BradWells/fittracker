package globalindustryinc.fittrackr;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


/**
 * Created by jccline on 10/5/2014.
 */
public class FittrackrActivity extends FragmentActivity {
    private NavigationDrawer dlDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fittracker);
        // Find our drawer exercise
        dlDrawer = (NavigationDrawer) findViewById(R.id.drawer_layout);
        // Setup drawer exercise
        dlDrawer.setupDrawerConfiguration((ListView) findViewById(R.id.lvDrawer),
                R.layout.drawer_nav_item, R.id.flContent);
        // Add nav items
        dlDrawer.addNavItem("Lifting",R.drawable.lifting,"Lifting", LiftingFragment.class);
        dlDrawer.addNavItem("Cardio",R.drawable.running, "Cardio", CardioFragment.class);
        dlDrawer.addNavItem("Measure",R.drawable.measure, "Measurements", MeasureFragment.class);
        dlDrawer.addNavItem("LiftingGraph",R.drawable.lifting,"LiftingGraph", LiftingGraphFragment.class);
        dlDrawer.addNavItem("CardioGraph",R.drawable.running, "CardioGraph", CardioGraphFragment.class);
        dlDrawer.addNavItem("MeasureGraph",R.drawable.measure, "MeasurementsGraphs", MeasureGraphFragment.class);
        dlDrawer.addNavItem("Timer", R.drawable.timer, "Timer", TimerFragment.class);
        dlDrawer.addNavItem("NutritionInfo", R.drawable.apple, "Nutrition", NutritionFragment.class);
        // Select default
        if (savedInstanceState == null) {
            dlDrawer.selectDrawerItem(0);
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content
        if (dlDrawer.isDrawerOpen()) {
            // Uncomment to hide menu items
            // menu.findItem(R.id.mi_test).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Uncomment to inflate menu items to Action Bar
        // inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (dlDrawer.getDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dlDrawer.getDrawerToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        dlDrawer.getDrawerToggle().onConfigurationChanged(newConfig);
    }

    public void startTimer(View view){
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
        startActivity(intent);
    }

}
