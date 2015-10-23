package com.iangclifton.moreessentials.lesson6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 6 examples.
 *
 * This demonstrates a simple custom view. When implementing your own view,
 * you can take shortcuts and avoid coding the parts that you know you
 * won't need, but it's a good idea to document it to explain expected
 * behavior.
 *
 * @author Ian G. Clifton
 */
public class Lesson6Fragment extends Fragment {

    public Lesson6Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson6, container, false);
    }


}
