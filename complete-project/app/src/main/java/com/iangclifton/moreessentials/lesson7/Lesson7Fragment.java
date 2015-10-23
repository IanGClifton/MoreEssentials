package com.iangclifton.moreessentials.lesson7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 7 examples.
 *
 * This Lesson starts from the basic custom view made in Lesson 6 and adds
 * additional features such as touch interactions and custom attributes.
 *
 * @author Ian G. Clifton
 */
public class Lesson7Fragment extends Fragment {

    public Lesson7Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson7, container, false);
    }


}
