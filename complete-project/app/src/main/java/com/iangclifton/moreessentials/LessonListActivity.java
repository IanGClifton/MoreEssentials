package com.iangclifton.moreessentials;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


/**
 * Activity to display the list of Lessons.
 *
 * On a larger device, this also shows the lesson details.
 *
 * @author Ian G. Clifton
 */
public class LessonListActivity extends AppCompatActivity
        implements LessonListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.lesson_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((LessonListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.lesson_list))
                    .setActivateOnItemClick(true);
        }

    }

    /**
     * Callback method from {@link LessonListFragment.Callbacks}
     * with the Lesson that was selected.
     */
    @Override
    public void onItemSelected(Lesson lesson) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Fragment fragment = null;
            try {
                fragment = lesson.getFragmentClass().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.lesson_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected Lesson.
            LessonDetailActivity.startActivity(this, lesson.getName(), lesson.getFragmentClass().getName());
        }
    }
}
