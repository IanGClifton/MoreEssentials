package com.iangclifton.moreessentials.lesson3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 3 examples.
 *
 * This Lesson is a very quick dive into SQL and SQLite, covering the basic
 * uses. SQL is a powerful way to query complex data structures, but we
 * demonstrated it with some fairly simple use cases in order to explain
 * the typical usage patterns. The important part to remember is that you
 * want to access the database from a background thread.
 *
 * @author Ian G. Clifton
 */
public class Lesson3Fragment extends Fragment implements View.OnClickListener {

    // SELECT FROM person WHERE age > 20 ORDER BY age ASC LIMIT 5

    public Lesson3Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lesson3, container, false);
        rootView.findViewById(R.id.button).setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        DbHelper.addClick(getActivity(), System.currentTimeMillis());

        DbHelper.getLastFiveClickTimes(getActivity(), new TimestampHandler());
    }
}
