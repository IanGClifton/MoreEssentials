package com.iangclifton.moreessentials;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Activity to display a single Lesson Fragment, used for phones.
 *
 * @author Ian G. Clifton
 */
public class LessonDetailActivity extends AppCompatActivity {

    private static final String ARG_FRAGMENT_CLASS = "fragmentClass";
    private static final String ARG_LESSON_NAME = "lessonName";

    /**
     * Static helper method to start the LessonDetailActivity with the right Intent extras
     *
     * @param context Context to create the Intent with
     * @param lessonName String name of the Lesson to display
     * @param fragmentClass String name of the Fragment class
     */
    public static void startActivity(Context context, String lessonName, String fragmentClass) {
        final Intent intent = new Intent(context, LessonDetailActivity.class);
        intent.putExtra(ARG_FRAGMENT_CLASS, fragmentClass);
        intent.putExtra(ARG_LESSON_NAME, lessonName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra(ARG_LESSON_NAME));
        setContentView(R.layout.activity_lesson_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            String fragmentClassString = getIntent().getStringExtra(ARG_FRAGMENT_CLASS);
            Class<Fragment> fragmentClass;
            try {
                fragmentClass = (Class<Fragment>) Class.forName(fragmentClassString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }

            Fragment fragment = null;
            try {
                fragment = fragmentClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return;
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.lesson_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, LessonListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
